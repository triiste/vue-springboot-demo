<template>
    <div style="padding: 10px">
        <!--    功能区域-->
        <div style="margin: 10px 0">
            <el-button type="primary" @click="add" v-if="this.officeid === this.user.userid || this.user.role === 2"  class="ml-5" style="margin-right: 0px">新增</el-button>

            <!--<el-popconfirm-->
                    <!--class="ml-5"-->
                    <!--confirm-button-text='确定'-->
                    <!--cancel-button-text='我再想想'-->
                    <!--icon="el-icon-info"-->
                    <!--icon-color="red"-->
                    <!--title="您确定批量删除这些数据吗？"-->
                    <!--@confirm="delBatch"-->
            <!--&gt;-->
                <!--<el-button type="danger" slot="reference"  style="margin-right: 5px" >批量删除 <i class="el-icon-remove-outline"></i></el-button>-->

            <!--</el-popconfirm>-->
            <el-popconfirm title="确认删除吗？" @confirm="delBatch">
                <template #reference>
                    <el-button type="danger" slot="reference"  style="margin-right: 5px" >批量删除</el-button>
                </template>
            </el-popconfirm>

            <el-upload action="http://182.92.125.156:9096/user/import" :show-file-list="false" accept="xlsx" :on-success="handleExcelImportSuccess" style="display: inline-block">
                <el-button type="primary" class="ml-5" style="margin-right: 5px">导入 <i class="el-icon-bottom"></i></el-button>
            </el-upload>
            <el-button type="primary" @click="exp" class="ml-5" style="margin-right: 5px">导出 <i class="el-icon-top"></i></el-button>
            <el-button type="primary" @click="returnhere"  class="ml-5" style="margin-right: 5px">返回室列表</el-button>
        </div>
        <!--    搜索区域-->
        <!--<div style="margin: 10px 0">-->
        <!--<el-input-->
        <!--style="width: 20%"-->
        <!--v-model="search"-->
        <!--size="large"-->
        <!--placeholder="请输入姓名"-->
        <!--clearable-->
        <!--/>-->
        <!--<el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>-->
        <!--</div>-->
        <el-table :data="tableData" border stripe :header-cell-class-name="headerBg"  @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="userid" label="ID" sortable/>
            <el-table-column prop="userjobid" label="工号" sortable/>
            <el-table-column prop="username" label="用户名"/>
            <!--      数据库里是下划线 对应前台代码会转成驼峰 mybatisplus这个框架帮做的-->
            <el-table-column prop="telephone" label="电话"/>
            <el-table-column prop="age" label="年龄"/>
            <el-table-column prop="sex" label="性别"/>
            <el-table-column prop="address" label="地址"/>
            <!--&lt;!&ndash;<el-table-column prop="role" label="角色">&ndash;&gt;-->
            <!--&lt;!&ndash;prop是该变量显示的内容&ndash;&gt;-->
            <!--<template #default="scope">-->
            <!--<span v-if="scope.row.role === 1">普通员工</span>-->
            <!--<span v-if="scope.row.role === 2">项目组长</span>-->
            <!--<span v-if="scope.row.role === 3">室主任</span>-->
            <!--<span v-if="scope.row.role === 4">科研处</span>-->
            <!--<span v-if="scope.row.role === 5">总后台管理员</span>-->
            <!--</template>-->
            <!--</el-table-column>-->
            <el-table-column prop="position" label="职位"/>
            <el-table-column prop="permission" label="部门"/>
            <el-table-column prop="mark" label="研究室奖励积分"/>
            <el-table-column prop="placeMark" label="所领导奖励积分"/>
            <el-table-column fixed="right" label="操作" width="120"  v-if="this.officeid === this.user.userid || this.user.role === 4">
                <!--v-if="this.user.userid === this.asd1"-->
                <template #default="scope" >
                    <el-button link type="primary" size="small" @click="handleEdit(scope.row)" v-if="this.officeid === this.user.userid || this.user.role === 4 ">编辑</el-button>
                    <el-popconfirm title="确认删除吗？" @confirm="handleDelete(scope.row.userid)">
                        <template #reference>
                            <el-button link type="danger" size="small" v-if="this.officeid === this.user.userid || this.user.role === 4">删除</el-button>
                        </template>
                    </el-popconfirm>

                </template>
            </el-table-column>

            <!--//所领导
            <el-table-column fixed="right" label="操作" width="120">
                &lt;!&ndash;v-if="this.user.userid === this.asd1"&ndash;&gt;
                <template #default="scope" >
                    <el-button link type="primary" size="small" @click="handleEdit(scope.row)" v-if="this.user.role === 4">编辑</el-button>
                    <el-popconfirm title="确认删除吗？" @confirm="handleDelete(scope.row.userid)">
                        <template #reference>
                            <el-button link type="danger" size="small" v-if="this.user.role === 4">删除</el-button>
                        </template>
                    </el-popconfirm>

                </template>
            </el-table-column>-->

            <el-table-column fixed="right" label="项目管理" width="120">
                <template #default="scope">
                    <el-button type="primary"  @click="join(scope.row)" style="margin-left: 2px;margin-bottom: 2px">参与项目</el-button>

                    <el-button type="primary"  @click="host(scope.row)" style="margin-left: 2px">主持项目</el-button>
                </template>
            </el-table-column>
        </el-table>

        <div style="margin: 10px 0">
            <el-pagination
                    v-model:currentPage="currentPage"
                    :page-sizes="[5, 10, 20]"
                    :page-size="pageSize"
                    :disabled="disabled"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total"
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
            />

            <!--      编辑弹窗-->
            <el-dialog
                    v-model="dialogVisible"
                    title="提示"
                    width="30%"
            >
                <el-form :model="form" label-width="120px" >
                    <el-form-item label="所属科室">
                        <el-input v-model="form.officeid" style="width: 70%" disabled/>
                    </el-form-item>
                    <el-form-item label="工号">
                        <el-input v-model="form.userjobid" style="width: 70%" />
                    </el-form-item>
                    <el-form-item label="用户名">
                        <el-input v-model="form.username" style="width: 70%"/>
                    </el-form-item>
                    <el-form-item label="电话">
                        <el-input v-model="form.telephone" style="width: 70%"/>
                    </el-form-item>
                    <el-form-item label="年龄">
                        <el-input v-model="form.age" style="width: 70%"/>
                    </el-form-item>
                    <el-form-item label="性别">
                        <el-radio-group v-model="form.sex">
                            <el-radio label="男" size="large">男</el-radio>
                            <el-radio label="女" size="large">女</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item label="地址">
                        <el-input type="textarea" v-model="form.address" style="width: 70%"/>
                    </el-form-item>
                    <!--<el-form-item label="角色">-->
                    <!--<el-form-item :model="form">-->
                    <!--&lt;!&ndash;lable标签是表示值的&ndash;&gt;-->
                    <!--<el-radio v-model="form.role" :label="1" style="color: black">普通员工</el-radio>-->
                    <!--<el-radio v-model="form.role" :label="2" style="color: black">项目组长</el-radio>-->
                    <!--<el-radio v-model="form.role" :label="3" style="color: black">室主任</el-radio>-->
                    <!--<el-radio v-model="form.role" :label="4" style="color: black">科研处</el-radio>-->
                    <!--<el-radio v-model="form.role" :label="5" style="color: black">总后台管理员</el-radio>-->
                    <!--</el-form-item>-->
                    <!--</el-form-item>-->
                    <el-form-item label="职位">
                        <el-input v-model="form.position" style="width: 70%"/>
                    </el-form-item>
                    <el-form-item label="部门">
                        <el-input type="textarea" v-model="form.permission" style="width: 70%"/>
                    </el-form-item>
                    <el-form-item label="研究室奖励积分" v-if="this.user.role === 2 || this.user.role === 3">
                        <el-input type="number" v-model="form.mark" style="width: 70%" ></el-input>
                    </el-form-item>
                    <el-form-item label="所领导奖励积分" v-if="this.user.role === 2 || this.user.role === 4">
                    <el-input type="number" v-model="form.placeMark" style="width: 70%"></el-input>
                    </el-form-item>

                </el-form>

                <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="save">确定</el-button>
        <el-button @click="dialogVisible = false">取消</el-button>
      </span>
                </template>
            </el-dialog>
        </div>
    </div>
</template>

<script>


    import request from "@/utils/request";

    export default {
        name: 'User',
        components: {},
        data() {
            return {
                form: {},
                asd1:0,
                dialogVisible: false,
                search:'',
                user:{},
                importaddress:"",
                currentPage: 1,
                pageSize: 10,
                total: 0,
                headerBg: 'headerBg',
                tableData: [],
                officeid:0,
                exportaddress:"",
                multipleSelection:[],
            }
        },
        created() {
            let userStr = localStorage.getItem("user") || "{}";
            this.user = JSON.parse(userStr);
            //this.role = JSON.parse(localStorage.getItem("user")).role;  //取缓存里面的值
            //请求服务端，确认当前登录用户的合法信息，服务器传不过来
            //下面这是一种交互方式，传来后台的form表单
            //传值来的数据是字符串类型，切记切记！！！！！！！
            this.message1 = this.$route.query.message; //传来的室内id
            this.asd1 = this.$route.query.asd1;
            // this.importaddress="http://localhost:9090/user/import?officeid="+this.message1;
            request.get("/user/" + this.user.userid).then(res => {
                if (res.code === '0') {
                    this.user = res.data
                }
            });

            console.log("我倒要看看此时的asd1"+this.asd1+"看看此数据的类型"+typeof(this.asd1));
            this.officeid = this.asd1 * 1;
            console.log(typeof(this.officeid));
            this.search = this.message1;
            this.load();

        },
        methods: {
            exp() {
                this.exportaddress= "http://182.92.125.156:9096/user/export?userid="+ this.message1;
                window.open(this.exportaddress)
            },
            load() {
                request.get("/user", {
                    params: {
                        pageNum: this.currentPage,
                        pageSize: this.pageSize,
                        search: this.search
                    }
                }).then(res => {
                    console.log(res)
                    this.tableData = res.data.records;

                    this.total = res.data.total
                });
                console.log(this.form)
            },
            join(row) {
                this.$router.push({
                    path:'/officejoinproject',
                    query: {
                        jmessage: row.userid,
                        message:this.message1,
                    }
                })
            },
            returnhere(){
                this.$router.push({
                    path:'/officeperson',
                })
            },
            delBatch() {

                let ids = this.multipleSelection.map(v => v.userid);  // [{}, {}, {}] => [1,2,3]
                console.log("看看选中的id"+ids);
                request.post("/user/del/batch", ids).then(res => {
                    if (res) {
                        this.$message.success("批量删除成功")
                        this.load()
                    } else {
                        this.$message.error("批量删除失败")
                    }
                })
            },
            handleExcelImportSuccess() {
                this.$message.success("导入成功");
                this.load()
            },
            host(row) {
                this.$router.push({
                    path:'/officehostproject',
                    query: {
                          userid:row.userid,
                         message: this.message1,
                    }
                })
            },
            add() {
                this.dialogVisible = true;
                this.form = {};
                this.form.officeid =this.message1;
            },
            save() {
                if (this.form.userid) {//更新
                    request.put("/user", this.form).then(res => {//.then是es6里的语法
                        console.log(res);
                        if (res.code === '0') {
                            this.$message({
                                type: "success",
                                message: "更新成功！"
                            })
                        } else {
                            this.$message({
                                type: "error",
                                message: res.msg
                            })
                        }
                        this.load();//刷新表格数据
                        this.dialogVisible = false;//关闭弹窗
                    })
                } else {//插入
                    request.post("/user", this.form).then(res => {//.then是es6里的语法
                        console.log(res)
                        if (res.code === '0') {
                            this.$message({
                                type: "success",
                                message: "新增成功！"
                            })
                        } else {
                            this.$message({
                                type: "error",
                                message: res.msg
                            })
                        }
                        this.load();//刷新表格数据
                        this.dialogVisible = false;//关闭弹窗
                    })
                }

            },
            handleEdit(row) {
                this.form = JSON.parse(JSON.stringify(row))
                this.dialogVisible = true
            },
            handleSelectionChange(val) {
                // console.log(val);
                this.multipleSelection = val
            },
            handleDelete(id) {
                console.log(id);
                request.delete("/user/" + id).then(res => {
                    if (res.code === '0') {
                        this.$message({
                            type: "success",
                            message: "删除成功！"
                        })
                    } else {
                        this.$message({
                            type: "error",
                            message: res.msg
                        })
                    }
                    this.load()//重新加载
                })
            },
            handleSizeChange(pageSize) { //改变当前页的个数触发
                this.pageSize = pageSize;
                this.load()
            },
            handleCurrentChange(pageNum) { // 改变当前页码触发
                this.currentPage = pageNum
                this.load()
            }
        }
    }
</script>
