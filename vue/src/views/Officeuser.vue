<template>
    <div style="padding: 10px">
        <!--    功能区域-->
        <!--<div style="margin: 10px 0">-->
            <!--<el-button type="primary" @click="add">新增</el-button>-->
        <!--</div>-->
        <!--    搜索区域-->
        <div style="margin: 10px 0">
            <el-input
                    style="width: 20%"
                    v-model="search"
                    size="large"
                    placeholder="请输入工号或者用户名"
                    clearable
            />
            <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
            <el-select v-model="value" disabled class="m-2" placeholder="Select" size="Select" style="margin-left: 5px" >
                <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                />
            </el-select>
        </div>
        <el-table :data="tableData" stripe border style="width: 100%">
            <el-table-column prop="userid" label="用户工号" sortable/>
            <el-table-column prop="username" label="用户名"/>
            <!--      数据库里是下划线 对应前台代码会转成驼峰 mybatisplus这个框架帮做的-->
            <!--<el-table-column prop="telephone" label="电话"/>-->
            <el-table-column prop="officeid" label="所属室"/>
            <!--<el-table-column prop="sex" label="性别"/>-->
            <!--<el-table-column prop="address" label="地址"/>-->
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
            <!--<el-table-column prop="position" label="职位"/>-->
            <!--<el-table-column prop="permission" label="部门"/>-->
            <el-table-column prop="projectmark" label="项目积分和" sortable/>
            <el-table-column prop="mark" label="研究室奖励积分" sortable/>
            <el-table-column prop="placeMark" label="所领导奖励积分" sortable/>
            <el-table-column prop="totalMark" label="积分总和" sortable/>
            <el-table-column prop="joincount" label="参与项目个数" sortable/>
            <el-table-column prop="joinmark" label="参与项目总积分" sortable/>
            <el-table-column prop="hostcount" label="主持项目个数" sortable/>
            <el-table-column prop="hostmark" label="主持项目总积分" sortable/>
            <!--<el-table-column fixed="right" label="操作" width="120">-->
                <!--<template #default="scope">-->
                    <!--<el-button link type="primary" size="small" @click="handleEdit(scope.row)">编辑</el-button>-->
                    <!--<el-popconfirm title="确认删除吗？" @confirm="handleDelete(scope.row.userid)">-->
                        <!--<template #reference>-->
                            <!--<el-button link type="danger" size="small">删除</el-button>-->
                        <!--</template>-->
                    <!--</el-popconfirm>-->

                <!--</template>-->
            <!--</el-table-column>-->
        </el-table>
            <!--div显示不出来-->
        <div style="margin: 10px 0">
            <el-pagination
                    v-model:currentPage="currentPage"
                    :page-sizes="[10,20,50,100,total]"
                    :page-size="pageSize"
                    :disabled="disabled"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total"
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"></el-pagination>

            <!--      弹窗-->
            <el-dialog
                    v-model="dialogVisible"
                    title="提示"
                    width="30%"
            >
                <el-form :model="form" label-width="120px" >
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
                    <!--<el-form-item label="积分">-->
                        <!--<el-input type="number" v-model="form.mark" style="width: 70%"/>-->
                    <!--</el-form-item>-->

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
                dialogVisible: false,
                search:'',
                user:{},
                value:0,
                currentPage: 1,
                pageSize: 10,
                total: 0,
                tableData: [],
               options:[
                   {
                       value:0,
                       label:'全所',
                   },
                    {
                        value: 1,
                        label: '1室',
                    },
                    {
                        value: 2,
                        label: '2室',
                    },
                    {
                        value: 3,
                        label: '3室',
                    },
                    {
                        value: 4,
                        label: '4室',
                    },
                    {
                        value: 5,
                        label: '5室',
                    },
                ]
            }
        },
        created() {

           // search = '迟';
            let userStr = localStorage.getItem("user") || "{}"
            this.user = JSON.parse(userStr)
            //this.role = JSON.parse(localStorage.getItem("user")).role;  //取缓存里面的值
            //请求服务端，确认当前登录用户的合法信息，服务器传不过来
            //下面这是一种交互方式，传来后台的form表单
            request.get("/user/" + this.user.userid).then(res => {
                if (res.code === '0') {
                    this.user = res.data
                }
            })
            this.value=this.user.officeid;
            this.load();
        },
        methods: {

            load() {


                console.log("看看此时的this."+this.user.officeid);
                console.log("看看此时的this.valuse"+this.value);
                request.get("/user/test", {
                    params: {
                        pageNum: this.currentPage,
                        pageSize: this.pageSize,
                        search: this.search,
                        officeid:this.value,

                    }
                }).then(res => {
                    // console.log(res)
                    this.tableData = res.data;
                    this.total = res.total
                    // console.log(this.total)
                })
            },
            office(){
                this.load();
            },
            add() {
                this.dialogVisible = true;
                this.form = {}
            },
            save() {
                if (this.form.userid) {//更新
                    request.put("/user", this.form).then(res => {//.then是es6里的语法
                        console.log(res)
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
                        this.load()//刷新表格数据
                        this.dialogVisible = false;//关闭弹窗
                    })
                } else {//插入
                    request.post("/user", this.form).then(res => {//.then是es6里的语法
                        console.log(res)
                        if (res.code == '0') {
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
                        this.load()//刷新表格数据
                        this.dialogVisible = false;//关闭弹窗
                    })
                }

            },
            handleEdit(row) {
                this.form = JSON.parse(JSON.stringify(row))
                this.dialogVisible = true
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
