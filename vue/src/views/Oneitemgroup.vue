<template>
    <div style="padding: 10px">
        <!--    功能区域-->
        <div style="margin: 10px 0">
            <!--<el-button type="primary" @click="add" v-if="this.user.role !== 3 && this.user.role !== 4">-->
            <el-button type="primary" @click="add" v-if=" this.user.role !== 4">新增</el-button>
            <!--<el-button type="primary" @click="returnhere" v-if="this.user.role === 3">返回上一步</el-button>-->
        </div>
        <el-table :data="tableData" stripe border  style="width: 100%">
            <el-table-column prop="id" label="ID" sortable></el-table-column>
            <el-table-column prop="projectid" label="项目ID" sortable></el-table-column>
            <el-table-column prop="userid" label="用户ID"></el-table-column>
            <!--      数据库里是下划线 对应前台代码会转成驼峰 mybatisplus这个框架帮做的-->
<!--            <el-table-column prop="mark" label="积分"  />-->
<!--            <el-table-column prop="handler" label="操作员"  />-->
            <el-table-column prop="projectProgress" label="项目进程"></el-table-column>
            <el-table-column prop="firstMark" label="第一阶段积分"></el-table-column>
            <el-table-column prop="secondMark" label="第二阶段积分"></el-table-column>
            <el-table-column prop="threeMark" label="第三阶段积分"></el-table-column>
            <!--<el-table-column prop="groupReward" label="附加分"></el-table-column>-->
            <!--<el-table-column prop="scientific" label="科研处确认情况">-->
                <!--&lt;!&ndash;prop是该变量显示的内容&ndash;&gt;-->
                <!--<template #default="scope">-->
                    <!--<span v-if="scope.row.scientific === 0" style="color: red">科研处未确认</span>-->
                    <!--<span v-if="scope.row.scientific === 1" style="color: blue">科研处已确认</span>-->
                <!--</template>-->
            <!--</el-table-column>-->
                <el-table-column fixed="right" label="操作" width="120" >
                <template #default="scope">
                    <el-button type="primary" size="small"   @click="perMessage(scope.row)">个人信息</el-button>
                    <el-button link type="primary" size="small" @click="handleEdit(scope.row) "  v-if="this.user.role !== 4">编辑</el-button>
                    <el-popconfirm title="确认删除吗？" @confirm="handleDelete(scope.row.id)">
                        <template #reference>
                            <el-button link type="danger" size="small" v-if="this.user.role !== 4" >删除</el-button>
                        </template>
                    </el-popconfirm>
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

            <!--      新增弹窗-->
            <el-dialog
                    v-model="dialogVisible"
                    title="提示"
                    width="30%"
            >
                <el-form :model="form" label-width="120px">
                    <el-form-item label="项目ID" >
                        <el-input v-model="form.projectid" style="width: 70%" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="用户ID">
                        <el-input v-model="form.userid" style="width: 70%"></el-input>
                    </el-form-item>
                    <el-form-item label="项目进度">
                        <el-input v-model="form.projectProgress" style="width: 70%"></el-input>
                    </el-form-item>
                </el-form>
                <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="save">确定</el-button>
        <el-button @click="dialogVisible = false">取消</el-button>
      </span>
                </template>
            </el-dialog>


            <!--      编辑弹窗-->
            <el-dialog
                    v-model="dialog"
                    title="提示"
                    width="30%"
            >
                <el-form :model="form" label-width="120px">
                    <el-form-item label="项目ID" >
                        <el-input v-model="form.projectid" style="width: 70%" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="用户ID">
                        <el-input v-model="form.userid" style="width: 70%" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="项目进度">
                        <el-input v-model="form.projectProgress" style="width: 70%"></el-input>
                    </el-form-item>
                    <el-form-item label="第一阶段积分比">
                        <el-input v-model="form.first" style="width: 70%"></el-input>%
                    </el-form-item>
                    <el-form-item label="第二阶段积分比">
                        <el-input v-model="form.second" style="width: 70%"></el-input>%
                    </el-form-item>
                    <el-form-item label="第三阶段积分比">
                        <el-input v-model="form.three" style="width: 70%"></el-input>%
                    </el-form-item>
                    <!--<el-form-item label="附加分">-->
                        <!--<el-input v-model="form.groupReward" style="width: 70%"></el-input>-->
                    <!--</el-form-item>-->
                </el-form>
                <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="save">确定</el-button>
        <el-button @click="dialog = false">取消</el-button>
      </span>
                </template>
            </el-dialog>


            <!--      个人信息弹窗-->
            <el-dialog
                    v-model="perdialog"
                    title="提示"
                    width="30%"
            >
                <el-form :model="form" label-width="120px">
                    <el-form-item label="ID">
                        <el-input v-model="form.userid" style="width: 70%" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="工号">
                        <el-input v-model="form.userjobid" style="width: 70%" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="用户名">
                        <el-input v-model="form.username" style="width: 70%" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="电话" >
                        <el-input v-model="form.telephone" style="width: 70%" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="年龄">
                        <el-input v-model="form.age" style="width: 70%" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="性别">
                        <el-input v-model="form.sex" style="width: 70%" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="地址">
                        <el-input v-model="form.address" style="width: 70%" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="职位">
                        <el-input v-model="form.position" style="width: 70%" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="部门">
                        <el-input v-model="form.permission" style="width: 70%" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="所属室">
                        <el-input v-model="form.officeid" style="width: 70%" disabled></el-input>
                    </el-form-item>
                    <!--<el-form-item label="个人积分">-->
                        <!--<el-input v-model="form.mark" style="width: 70%" disabled></el-input>-->
                    <!--</el-form-item>-->
                </el-form>
                <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="save">确定</el-button>
        <el-button @click="perdialog = false">取消</el-button>
      </span>
                </template>
            </el-dialog>
        </div>
    </div>
</template>
<script>


    import request from "@/utils/request";

    export default {
        name: 'Group',
        components: {
        },
        data(){
            return{
                form:{},
                dialogVisible:false,
                dialog:false,
                perdialog:false,
                search:'',
                user:{},
                projectid1 :'',
                currentPage:1,
                pageSize:10,
                total:0,
                tableData:[],
                message1:"",
                asd1:"",
                asd2:"",
                asd3:"",
                sum:0,
                sum1:0,
                sum2:0,
            }
        },
        created() {
            this.message1 = this.$route.query.message; //传来的项目ID
            this.asd1 = this.$route.query.asd1; //传来项目的第一阶段积分
            this.asd2 = this.$route.query.asd2; //传来项目的第二阶段积分
            this.asd3 = this.$route.query.asd3; //传来项目的第三阶段积分
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
            this.search = this.message1;
            this.load();
        },


        methods:{
            load(){
                request.get("/group",{
                    params:{
                        pageNum: this.currentPage,
                        pageSize: this.pageSize,
                        search: this.search
                    }
                }).then(res =>{
                    console.log(res);
                    this.tableData = res.data.records;
                    this.total = res.data.total;
                    //  this.projectid1 = res.data.records[0].projectid;
                    // console.log(this.projectid1);

                    //   this.projectid1 = JSON.parse(JSON.stringify(res.data.records.row))
                    // console.log(projectid.projectid);
                })
            },
            // returnhere(){
            //     this.$router.push({
            //         path:'/officehostproject',
            //         query: {
            //             userid:row.userid,
            //             // message: row.projectId,
            //         }
            //     })
            // },
            perMessage(row) {
                console.log(row.userid);

                request.get("/user/" + row.userid).then(res => {
                    if (res.code === '0') {
                        this.form = res.data;
                    }
                });

                this.form = JSON.parse(JSON.stringify(row));

                this.perdialog = true
            },

            add(){
                this.dialogVisible = true;
                this.form = {};
                this.form.projectid =this.search;
            },

            save(){
                if(this.form.id){//更新
                    this.form.firstMark=this.form.first*this.asd1*0.01;
                    this.form.secondMark=this.form.second*this.asd2*0.01;
                    this.form.threeMark=this.form.three*this.asd3*0.01;
                    this.sum=(parseFloat( this.form.first) + parseFloat(this.sum)).toFixed(2);
                    this.sum1=(parseFloat( this.form.second) + parseFloat(this.sum1)).toFixed(2);
                    this.sum2=(parseFloat( this.form.three) + parseFloat(this.sum2)).toFixed(2);
                    if(parseFloat(this.sum) <= parseFloat(100) && parseFloat(this.sum1) <= parseFloat(100) && parseFloat(this.sum2) <= parseFloat(100)){
                        request.put("/group",this.form).then(res =>{//.then是es6里的语法
                            console.log(res);
                            if(res.code === '0'){
                                this.$message({
                                    type:"success",
                                    message:"更新成功！"
                                })
                            }else{
                                this.$message({
                                    type:"error",
                                    message: res.msg
                                })
                            }
                            // this.dialog = false;//关闭编辑弹窗
                            this.load();//刷新表格数据
                        })
                    }else{
                        this.$message({
                            type:"error",
                            message:"项目分配总占比已经大于100%了！！！",
                        });
                        //   this.load();//刷新表格数据
                    }
                    this.dialog = false;//关闭编辑弹窗
                } else{//插入
                    request.post("/group",this.form).then(res =>{//.then是es6里的语法
                        if(res.code === '0'){
                            this.$message({
                                type:"success",
                                message:"新增成功！"
                            })
                        }else{
                            this.$message({
                                type:"error",
                                message: res.msg
                            })
                        }
                        this.load();//刷新表格数据
                        this.dialogVisible = false;//关闭新增弹窗
                    })
                }

            },

            handleEdit(row){
                this.sum=0;
                //将第一阶段总积分计算出来始终是总分
                for( var i = 0 ; i < this.tableData.length ; i++) {
                    this.sum +=this.tableData[i].first;
                }
                this.sum1=0;
                //将第二阶段总积分计算出来始终是总分
                for( var j = 0 ; j < this.tableData.length ; j++) {
                    this.sum1 +=this.tableData[j].second;
                }
                this.sum2=0;
                //将第三阶段总积分计算出来始终是总分
                for( var k = 0 ; k < this.tableData.length ; k++) {
                    this.sum2 +=this.tableData[k].three;
                }
                // console.log("我要看看sum2"+this.sum2);
                this.sum=(this.sum-row.first).toFixed(2);
                this.sum1=(this.sum1-row.second).toFixed(2);
                this.sum2=(this.sum2-row.three).toFixed(2);
                this.form = JSON.parse(JSON.stringify(row));

                this.dialog = true
            },
            handleDelete(id){
                console.log(id);
                request.delete("/group/"+id).then(res =>{
                    if(res.code === '0'){
                        this.$message({
                            type:"success",
                            message:"删除成功！"
                        })
                    }else{
                        this.$message({
                            type:"error",
                            message: res.msg
                        })
                    }
                    this.load()//重新加载
                })
            },
            handleSizeChange(pageSize){ //改变当前页的个数触发
                this.pageSize = pageSize;
                this.load()
            },
            handleCurrentChange(pageNum){ // 改变当前页码触发
                this.currentPage = pageNum;
                this.load()
            }
        }
    }
</script>
