<template>
    <div style="padding: 10px">
        <!--    功能区域-->
        <div style="margin: 10px 0" v-if="this.user.role != 1 && this.user.role != 2">
            <el-button type="primary" @click="add">新增</el-button>
        </div>
        <!--    搜索区域-->
        <div style="margin: 10px 0" v-if="this.user.role != 2 && this.user.role != 1">
            <el-input
                    style="width: 20%"
                    v-model="search"
                    size="large"
                    placeholder="请输入项目ID或者组长工号"
                    clearable
            />
            <el-button type="primary" style="margin-left: 5px" @click="load" >查询</el-button>
        </div>
        <el-table :data="tableData" stripe border style="width: 100%">
            <!--<template>-->
            <el-table-column prop="projectId" label="ID" sortable/>
            <el-table-column prop="projectItemid" label="项目编号" sortable/>
            <el-table-column prop="projectName" label="项目名"/>
            <!--      数据库里是下划线 对应前台代码会转成驼峰 mybatisplus这个框架帮做的-->
            <el-table-column prop="groupId" label="组号"/>
            <el-table-column prop="projectgroupId" label="项目组长ID"/>
            <el-table-column prop="projectDetial" label="研究内容"/>
            <el-table-column prop="projectCycle" label="研究周期"/>
            <el-table-column prop="projectProgress" label="项目进度"/>
            <el-table-column prop="projectProduct" label="成果形式"/>
            <el-table-column prop="projectType" label="研究类别"/>
            <el-table-column prop="projectLevel" label="研究级别"/>
            <el-table-column prop="projectMark" label="项目积分"/>
            <el-table-column prop="firstMark" label="第一阶段积分"></el-table-column>
            <el-table-column prop="secondMark" label="第二阶段积分"></el-table-column>
            <el-table-column prop="threeMark" label="第三阶段积分"></el-table-column>
            <el-table-column prop="projectReward" label="奖励积分"/>

            <el-table-column fixed="right" label="操作" width="120" >
                <template #default="scope">
                    <!--v-if="this.user.userid === JSON.parse(JSON.stringify(row)).ProjectgroupId"-->
                    <!--v-if="this.user.userid === form.projectgroupId"-->
                    <el-button link type="primary" size="small" @click="handleEdit(scope.row)"
                    >个人积分情况
                    </el-button>

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
                    <el-form-item label="ID">
                        <el-input v-model="form.id" style="width: 70%" disabled/>
                    </el-form-item>
                    <el-form-item label="项目ID">
                        <el-input v-model="form.projectid" style="width: 70%" disabled/>
                    </el-form-item>
                    <el-form-item label="用户ID">
                        <el-input v-model="form.userid" style="width: 70%" disabled/>
                    </el-form-item>
                    <el-form-item label="用户积分">
                        <el-input v-model="form.mark" style="width: 70%" disabled/>
                    </el-form-item>
                    <el-form-item label="操作员">
                        <el-input v-model="form.handler" style="width: 70%" disabled/>
                    </el-form-item>
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
                    <el-form-item label="附加分">
                        <el-input v-model="form.groupReward" style="width: 70%"></el-input>
                    </el-form-item>


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
                k:0,
            }
        },
        created() {


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

            //this.search=this.user.userid;
            //能找到id
            // this.search=this.user.userid;
            //找到项目ID

                //查找当前group中存在的用户ID  put是放过去 get是请求过来
                // request.get("/group/find",{
                //     params: {
                //         userid:this.user.userid,
                //     }
                // }).then(res => {
                //     if (res !== -1) {
                //         this.search = res;
                //     }
                //     console.log(res);
                //     this.load()
                // });
            // this.search=自己参与的项目的编号 -->查找group表找到自己参与的项目ID，然后拿自己参与的ID取搜索取到自己参与的项目
            this.load();


        },

        methods: {
            load() {
                request.get("/project", {
                    params: {
                        pageNum: this.currentPage,
                        pageSize: this.pageSize,
                        search: this.search
                    }
                }).then(res => {
                    console.log(res);


                    this.totaltableData = res.data.records;
                    // for(this.i=0;this.i<this.totaltableData.length;this.i++){
                    //     this.sleep(1000);
                    //                       request.get("/group/finddata",{
                    //         params: {
                    //             userid:this.user.userid,
                    //             projectId:this.totaltableData[this.i].projectId,
                    //         }
                    //     }).then(res => {
                    //         // console.log(res);
                    //         if (res.code === '0') {
                    //             this.record[this.k]=1;
                    //             this.$message({
                    //                 type: "success",
                    //                 message:"有点快"+this.i,
                    //
                    //             });
                    //             console.log(this.tableData[0]);
                    //         } else {
                    //             this.$message({
                    //                 type: "error",
                    //                 message: "有点错误"+this.i,
                    //
                    //             })
                    //         }
                    //                           // this.k++;
                    //     });
                    // }

                    // this.totaltableData[0]="";
                    // this.totaltableData[1]="";
                    // this.totaltableData[this.j]=this.totaltableData[this.i];
                    // this.j++;
                    // this.totaltableData.length=3;
                    // this.totaltableData[2]="";
                    this.tableData=this.totaltableData;
                    this.total=res.data.total;
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
            //编辑权限
            handleEdit(row) {

           //     row.projectLevel="傻逼啦啦啦啦";
                //用id请求后台数据
                this.search =this.user.userid;
                //在用项目组id和本人id请求group，找到所在的这一行
                request.get("/group/finddata",{
                    params: {
                        userid:this.user.userid,
                        projectId:row.projectId,
                    }
                }).then(res => {

                    // console.log(res);

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
