<template>
    <div style="padding: 10px">
        <!--    功能区域-->
        <!--<div style="margin: 10px 0" v-if="this.user.role !== 1">-->
            <!--&lt;!&ndash;<el-button type="primary" @click="add">新增</el-button>&ndash;&gt;-->
            <!--<el-button type="primary" @click="returnhere"  >返回上一步</el-button>-->
        <!--</div>-->
        <!--    搜索区域-->
        <!--<div style="margin: 10px 0" v-if="this.user.role != 2 && this.user.role != 1">-->
        <!--<el-input-->
        <!--style="width: 20%"-->
        <!--v-model="search"-->
        <!--size="large"-->
        <!--placeholder="请输入项目ID或者组长工号"-->
        <!--clearable-->
        <!--/>-->
        <!--<el-button type="primary" style="margin-left: 5px" @click="load" >查询</el-button>-->
        <!--</div>-->
        <el-table :data="tableData" stripe border style="width: 100%">
            <el-table-column prop="date" label="立项时间" sortable></el-table-column>

            <el-table-column prop="projectId" label="项目编号" sortable></el-table-column>
            <!--<el-table-column prop="projectItemid" label="项目编号" sortable></el-table-column>-->
            <el-table-column prop="projectName" label="项目名"></el-table-column>
            <!--      数据库里是下划线 对应前台代码会转成驼峰 mybatisplus这个框架帮做的-->
            <!--            <el-table-column prop="groupId" label="组号"/>-->
            <el-table-column prop="projectgroupId" label="项目组长ID"></el-table-column>
            <!--            <el-table-column prop="projectDetial" label="研究内容"/>-->
            <!--            <el-table-column prop="projectCycle" label="研究周期"/>-->
            <el-table-column prop="projectProgress" label="项目进度"></el-table-column>
            <!--            <el-table-column prop="projectProduct" label="成果形式"/>-->

            <el-table-column prop="projectType" label="研究类别">  </el-table-column>
            <!--<el-table-column prop="projectLevel" label="研究级别"></el-table-column>-->
            <el-table-column prop="projectMark" label="项目积分"></el-table-column>
            <el-table-column prop="firstMark" label="第一阶段积分" ></el-table-column>
            <el-table-column prop="secondMark" label="第二阶段积分"></el-table-column>
            <el-table-column prop="threeMark" label="第三阶段积分"></el-table-column>
            <el-table-column prop="sumTotal" label="已得积分" sortable></el-table-column>
            <!--<el-table-column prop="projectReward" label="附加分"></el-table-column>-->

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
            <el-table-column fixed="right" label="操作" width="120" >
                <template #default="scope">
                    <!--v-if="this.user.userid === JSON.parse(JSON.stringify(row)).ProjectgroupId"-->
                    <!--v-if="this.user.userid === form.projectgroupId"-->
                    <el-button link type="primary" size="small" @click="handleEdit(scope.row)"
                    >个人积分情况
                    </el-button>
                    <!--<el-button link type="primary" size="small" @click="sConfirm(scope.row)" >室主任确认</el-button>-->
                    <!--<el-button link type="primary" size="small" @click="sConfirm(scope.row)" v-if="this.user.role === 2">科研处确认</el-button>-->

                </template>
            </el-table-column>
            <!--</template>-->
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

            <!--      弹窗-->
            <el-dialog
                    v-model="dialogVisible"
                    title="个人积分情况"
                    width="30%"
            >
                <el-form :model="form" label-width="120px" disabled>
                    <!--<el-form-item label="项目编号">-->
                        <!--<el-input v-model="form.id" style="width: 70%" disabled/>-->
                    <!--</el-form-item>-->
                    <el-form-item label="项目编号">
                        <el-input v-model="form.projectid" style="width: 70%" disabled/>
                    </el-form-item>
                    <el-form-item label="用户工号">
                        <el-input v-model="form.userid" style="width: 70%" disabled/>
                    </el-form-item>
                    <!--<el-form-item label="用户积分">-->
                        <!--<el-input v-model="form.mark" style="width: 70%" disabled/>-->
                    <!--</el-form-item>-->
                    <!--<el-form-item label="操作员">-->
                        <!--<el-input v-model="form.handler" style="width: 70%" disabled/>-->
                    <!--</el-form-item>-->
                    <el-form-item label="项目进度">
                        <el-input v-model="form.projectProgress" style="width: 70%" disabled/>
                    </el-form-item>
                    <el-form-item label="第一阶段积分">
                        <el-input v-model="form.firstMark" style="width: 70%"></el-input>
                    </el-form-item>
                    <el-form-item label="第二阶段积分">
                        <el-input v-model="form.secondMark" style="width: 70%"></el-input>
                    </el-form-item>
                    <el-form-item label="第三阶段积分">
                        <el-input v-model="form.threeMark" style="width: 70%"></el-input>
                    </el-form-item>
                    <!--<el-form-item label="附加分">-->
                        <!--<el-input v-model="form.groupReward" style="width: 70%"></el-input>-->
                    <!--</el-form-item>-->


                    <!--<el-form-item label="室主任确认情况">-->
                    <!--<el-input v-model="form.officeDirector" style="width: 70%" disabled/>-->
                    <!--</el-form-item>-->
                    <!--<el-form-item label="科研处确认情况">-->
                    <!--<el-input v-model="form.scientific" style="width: 70%" disabled/>-->
                    <!--</el-form-item>-->

                </el-form>

                <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="save">确定</el-button>
        <el-button @click="dialogVisible = false">取消</el-button>
      </span>
                </template>
            </el-dialog>
            <!--      室主任确认-->
            <el-dialog
                    v-model="dialog"
                    title="积分确认"
                    width="30%"
            >
                <el-form :model="form" label-width="120px">
                    <!--<el-form-item label="ID">
                        <el-input v-model="form.id" style="width: 70%" disabled/>
                    </el-form-item>-->
                    <el-form-item label="项目ID">
                        <el-input v-model="form.projectid" style="width: 70%" disabled/>
                    </el-form-item>
                    <el-form-item label="用户ID">
                        <el-input v-model="form.userid" style="width: 70%" disabled/>
                    </el-form-item>
                    <!--<el-form-item label="用户积分">
                        <el-input v-model="form.mark" style="width: 70%" disabled/>
                    </el-form-item>-->
                    <el-form-item label="第一阶段积分">
                        <el-input v-model="form.firstMark" style="width: 30%" disabled></el-input>
                        <div style="margin-left: 10px">
                            <el-button link type="primary"  @click="add">待确认</el-button>
                            <el-button link type="primary"  @click="add">取消确认</el-button>
                        </div>
                    </el-form-item>
                    <el-form-item label="第二阶段积分">
                        <el-input v-model="form.secondMark" style="width: 30%" disabled></el-input>
                        <div style="margin-left: 10px">
                            <el-button link type="primary"  @click="add">待确认</el-button>
                            <el-button link type="primary"  @click="add">取消确认</el-button>
                            <!--<span v-if="scope.row.scientific === 1" style="color: blue">科研处已确认</span>-->
                        </div>
                    </el-form-item>
                    <el-form-item label="第三阶段积分">
                        <el-input v-model="form.threeMark" style="width: 30%" disabled></el-input>
                        <div style="margin-left: 10px">
                            <el-button link type="primary"  @click="add">待确认</el-button>
                            <el-button link type="primary"  @click="add">取消确认</el-button>
                        </div>
                    </el-form-item>


                    <!--<el-form-item label="室主任确认情况">
                    <el-input v-model="form.officeDirector" style="width: 70%" disabled/>
                    </el-form-item>
                    <el-form-item label="科研处确认情况">
                    <el-input v-model="form.scientific" style="width: 70%" disabled/>
                    </el-form-item>-->

                </el-form>

                <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="save">确定</el-button>
        <el-button @click="dialog = false">取消</el-button>
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
                total: 0,
                tableData: [],
                totaltableData:[],
                i:0,
                j:0,
                record:[],
                gridData: [
                    {
                        details: '',
                    },
                ],
                dialog:false,
                k:0,
                message1:0,
                maanumber:0,
            }
        },
        created() {

            this.message1 = this.$route.query.jmessage; //传来的项目ID
            this.message2 = this.$route.query.message;
            this.maa = this.$route.query.mee; //传来的项目成员所在室的室主任id
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

            console.log(this.user.userid);//找到id2082
            this.load();

        },

        methods: {
            load() {
                //   待修改----------------------
                request.get("/project/test",{
                    params:{
                        userid: this.message1,
                        pageNum: this.currentPage,
                        pageSize: this.pageSize,
                    }
                }).then(res =>{
                    this.tableData=res.data;
                    console.log(res)
                    //先全部查出来再去限制共几条是有问题的
                    for(var i=0;i<this.tableData.length;i++){
                        // console.log(this.tableData[i].date.substring(0,10));
                        this.tableData[i].date=this.tableData[i].date.substring(0,10);
                        this.tableData[i].sumTotal=this.tableData[i].firstMark+this.tableData[i].secondMark+this.tableData[i].threeMark;
                    }

                    this.total = res.total;
                })
            },

            add() {
                this.dialogVisible = true;
                this.form = {}
            },
            save() {
                this.dialogVisible = false;//关闭弹窗
            },
            sleep(ms) { //sleep延迟方法2
                var unixtime_ms = new Date().getTime();
                while(new Date().getTime() < unixtime_ms + ms) {}
            },
            returnhere(){
                this.$router.push({
                    path:'/oneofficegroup',
                    query: {
                        message:  this.message2,
                    }
                })
            },
            //项目细节
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
            //室主任确认
            sConfirm(row) {
                console.log("这次过来了吗"+this.maa); //传过来的该成员所在室的室主任ID
                console.log("是谁登录的"+this.user.userid)  //登录的室主任ID
                //用id请求后台数据
                // this.search =this.user.userid;
                //在用项目组id和本人id请求group，找到所在的这一行
                console.log(typeof(this.maa))
                this.maanumber = this.maa * 1;
                console.log(typeof(this.maanumber))
                console.log(typeof(this.user.userid));
                if(this.maanumber === this.user.userid){
                  this.dialog=true;
                }
                request.get("/group/finddata", {
                    params: {
                        userid: this.message1,
                        projectId: row.projectId,
                        maa:this.maa,
                    }
                }).then(res => {

                    // console.log(res);

                    if (res.code === '0') {
                        this.form = res.data;
                        this.dialog = true
                    } else {
                        this.$message({
                            type: "error",
                            message: res.msg
                        })
                    }

                } );

            },

            //编辑权限
            handleEdit(row) {

                //用id请求后台数据
                // this.search =this.user.userid;
                //在用项目组id和本人id请求group，找到所在的这一行
                request.get("/group/finddata",{
                    params: {
                        userid:this.message1,
                        projectId:row.projectId,
                    }
                }).then(res => {

                     console.log(res);

                    if (res.code === '0') {
                        this.form=res.data;
                        this.dialogVisible = true
                    } else {
                        this.$message({
                            type: "error",
                            message: res.msg
                        })
                    }

                });
                //、、、、、、、、、、、、、、、、、、、、、、、、、、
                //    this.load();
                //
                //    this.form = JSON.parse(JSON.stringify(row));
                // //   this.form.projectLevel="傻逼";
                //    this.dialogVisible = true


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
