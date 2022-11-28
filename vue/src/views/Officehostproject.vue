<template>
    <div style="padding: 10px">
        <!--    功能区域-->
        <div style="margin: 10px 0" v-if="this.user.role != 1">
            <el-button type="primary" @click="add" v-if="this.user.role === 1">新增</el-button>
            <!--<el-button type="primary" @click="returnhere"  >返回上一步</el-button>-->
        </div>
        <!--    搜索区域-->
        <!--<div style="margin: 10px 0" v-if="this.user.role != 2 && this.user.role != 1">-->
        <!--<el-input-->
        <!--style="width: 20%"-->
        <!--v-model="search"-->
        <!--size="large"-->
        <!--placeholder="请输入项目ID或者组长工号"-->
        <!--clearable></el-input>-->
        <!--<el-button type="primary" style="margin-left: 5px" @click="load" >查询</el-button>-->
        <!--</div>-->
        <el-table :data="tableData" stripe border style="width: 100%">
            <!--<template>-->
            <el-table-column prop="date" label="立项时间" sortable></el-table-column>
            <el-table-column prop="projectId" label="项目编号" sortable></el-table-column>
            <!--            <el-table-column prop="projectItemid" label="项目编号" sortable/>-->
            <el-table-column prop="projectName" label="项目名"></el-table-column>
            <!--      数据库里是下划线 对应前台代码会转成驼峰 mybatisplus这个框架帮做的-->
            <!--            <el-table-column prop="groupId" label="组号"/>-->
            <el-table-column prop="projectgroupId" label="项目组长ID"></el-table-column>
            <!--            <el-table-column prop="projectDetial" label="研究内容"/>-->
            <!--            <el-table-column prop="projectCycle" label="研究周期"/>-->
            <el-table-column prop="projectProgress" label="项目进度"></el-table-column>
            <!--            <el-table-column prop="projectProduct" label="成果形式"/>-->
            <el-table-column prop="projectType" label="研究类别"></el-table-column>
            <!--<el-table-column prop="projectLevel" label="研究级别"></el-table-column>-->
            <el-table-column prop="projectMark" label="项目积分"></el-table-column>
            <el-table-column prop="firstMark" label="第一阶段积分"></el-table-column>
            <el-table-column prop="secondMark" label="第二阶段积分"></el-table-column>
            <el-table-column prop="threeMark" label="第三阶段积分"></el-table-column>
            <el-table-column prop="sumTotal" label="已得积分" sortable></el-table-column>
            <el-table-column prop="projectType"  label="项目内容">
                <template #default="scope">

                    <el-tooltip content="Top center" placement="top">
                        <!--<el-button>项目具体情况</el-button>-->
                        <el-popover
                                placement="bottom"
                                title=""
                                :width="400"
                                trigger="click"
                                content=this.details
                        >
                            <template #reference>
                                <!--@click="detailsproject(scope.row)"-->
                                <el-button @click="detailsproject(scope.row)">项目详情</el-button>
                            </template>
                            <el-table :data="gridData">
                                <el-table-column width="300" property="details" label="项目详情" />

                            </el-table>
                        </el-popover>
                    </el-tooltip>
                </template>
            </el-table-column>
            <!--<el-table-column prop="projectReward" label="附加分"></el-table-column>-->
            <el-table-column fixed="right" label="操作" width="120" >
                <template #default="scope">
                    <!--v-if="this.user.userid === JSON.parse(JSON.stringify(row)).ProjectgroupId"-->
                    <!--v-if="this.user.userid === form.projectgroupId"-->
                    <el-button  type="primary" size="small"   @click="details(scope.row)">成员详情</el-button>
                    <!--<el-button link type="primary" size="small" @click="handleEdit(scope.row)">编辑</el-button>-->
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
                    @current-change="handleCurrentChange"></el-pagination>

            <!--      编辑弹窗-->
            <el-dialog
                    v-model="dialogVisible"
                    title="提示"
                    width="30%"
            >
                <el-form :model="form" label-width="120px">
                    <el-form-item label="项目ID">
                        <el-input v-model="form.projectId" style="width: 70%" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="项目名称">
                        <el-input v-model="form.projectName" style="width: 70%" disabled></el-input>
                    </el-form-item>
                    <!--                    <el-form-item label="项目组号">-->
                    <!--                        <el-input v-model="form.groupId" style="width: 70%"/>-->
                    <!--                    </el-form-item>-->
                    <el-form-item label="项目组长ID">
                        <el-input v-model="form.projectgroupId" style="width: 70%" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="项目进度">
                        <el-input v-model="form.projectProgress" style="width: 70%"></el-input>
                    </el-form-item>
                    <el-form-item label="研究类别">
                        <el-input v-model="form.projectType" style="width: 70%" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="研究级别">
                        <el-input v-model="form.projectLevel" style="width: 70%" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="项目积分">
                        <el-input v-model="form.projectMark" style="width: 70%" disabled></el-input>
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
        name: 'Project',
        components: {},
        data() {
            return {
                form: {},
                dialogVisible: false,
                search: '',
                user: {},
                //  project:{},
                currentPage: 1,
                pageSize: 10,
                gridData: [
                    {
                        details: '',
                    },
                ],
                total: 0,
                tableData: []
            }
        },
        created() {
            this.message1 = this.$route.query.userid; //传来的项目ID
            this.message2 = this.$route.query.message;
            let userStr = localStorage.getItem("user") || "{}";
            this.user = JSON.parse(userStr);
            //this.role = JSON.parse(localStorage.getItem("user")).role;  //取缓存里面的值
            //请求服务端，确认当前登录用户的合法信息，服务器传不过来
            //下面这是一种交互方式，传来后台的form表单
            request.get("/user/" + this.user.userid).then(res => {
                if (res.code === '0') {
                    this.user = res.data
                }
            });
            console.log(this.message1);
            this.search=this.message1;
            this.load();
            // if(this.user.role === 1 ||this.user.role === 2  ){
            //     //查找当前group中存在的项目ID  put是放过去 get是请求过来
            //     request.get("/group/find",{
            //         params: {
            //             userid:this.user.userid,
            //         }
            //     }).then(res => {
            //         if (res !== -1) {
            //             this.search = res;
            //         }
            //         console.log(res);
            //         this.load()
            //     });
            // }
            // else{
            //     this.load()
            // }
        },


        methods: {
            load() {
                request.get("/project/host", {
                    params: {
                        pageNum: this.currentPage,
                        pageSize: this.pageSize,
                        search: this.search
                    }
                }).then(res => {
                    console.log(res);
                    this.tableData = res.data.records;
                    for(var i=0;i<this.tableData.length;i++){
                        // console.log(this.tableData[i].date.substring(0,10));
                        this.tableData[i].date=this.tableData[i].date.substring(0,10);
                        this.tableData[i].sumTotal=this.tableData[i].firstMark+this.tableData[i].secondMark+this.tableData[i].threeMark;
                    }
                    this.total = res.data.total;
                })
            },

            add() {
                this.dialogVisible = true;
                this.form = {}
            },
            save() {
                if (this.form.projectId) {//更新
                    request.put("/project", this.form).then(res => {//.then是es6里的语法
                        console.log(res);
                        if (res.code === '0') {
                            this.$message({
                                type: "success",
                                message: "更新成功！"
                            })
                        } else {
                            this.$message({
                                type: "error",
                                message:res.msg
                            })
                        }
                        this.load();//刷新表格数据
                        this.dialogVisible = false;//关闭弹窗
                    })
                } else {//插入
                    request.post("/project", this.form).then(res => {//.then是es6里的语法
                        console.log(res);
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
            returnhere(){
                this.$router.push({
                    path:'/oneofficegroup',
                    query: {
                        message: this.message2,
                    }
                })
            },
            //编辑权限
            handleEdit(row) {
                this.form = JSON.parse(JSON.stringify(row));

                this.dialogVisible = true


            },
            details(row) {
                // console.log(row.firstMark)
                //先得拿到id号，拿到id号后查找他的所有成员
                //      console.log(row.projectId);
                //       this.$router.push('/oneitemgroup');
                this.$router.push({
                    path:'/oneitemgroup',
                    query: {
                        message: row.projectId,
                        asd1:row.firstMark,
                        asd2:row.secondMark,
                        asd3:row.threeMark
                    }
                })

                // this.$router.push("/register")

            },
            detailsproject(row) {
                // console.log("看看这是啥呀"+row.projectkind);
                // console.log("看看这是啥呀"+typeof  row.projectkind);
                request.get("/project/finddetail",
                    {params: {projectkind: row.projectkind,}
                    }
                ).then(res =>{
                    // console.log("看看"+res.data);
                    // console.log(typeof res);
                    this.gridData[0].details=res.data;
                })
            },
            handleSizeChange(pageSize) { //改变当前页的个数触发
                this.pageSize = pageSize;
                this.load()
            },
            handleCurrentChange(pageNum) { // 改变当前页码触发
                this.currentPage = pageNum;
                this.load()
            }
        }
    }
</script>
