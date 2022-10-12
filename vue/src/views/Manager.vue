<template>
    <div style="padding: 10px">
        <!--    功能区域-->
        <!--<div style="margin: 10px 0">-->
            <!--<el-button type="primary" @click="add" v-if="this.user.role != 1">新增</el-button>-->
        <!--</div>-->
        <!--    搜索区域-->
        <div style="margin: 10px 0" v-if="this.user.role != 1">
            <el-input
                    style="width: 20%"
                    v-model="search"
                    size="large"
                    placeholder="请输入用户ID或者项目ID"
                    clearable
            />
            <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
        </div>
        <el-table :data="tableData" stripe border  style="width: 100%">
            <el-table-column prop="id" label="ID" sortable  />
            <el-table-column prop="projectid" label="项目ID" sortable  />
            <el-table-column prop="userid" label="用户ID"  />
            <!--      数据库里是下划线 对应前台代码会转成驼峰 mybatisplus这个框架帮做的-->
            <el-table-column prop="mark" label="积分"  />
            <el-table-column prop="handler" label="操作员"  />
            <el-table-column prop="projectProgress" label="项目进程"  />
            <el-table-column prop="officeDirector" label="室主任确认情况" sortable>
                <!--prop是该变量显示的内容-->
                <template #default="scope">
                    <span v-if="scope.row.officeDirector === 0" style="color: red">室主任未确认</span>
                    <span v-if="scope.row.officeDirector === 1" style="color: blue">室主任已确认</span>
                </template>
            </el-table-column>
            <el-table-column prop="scientific" label="科研处确认情况"  >
                <!--prop是该变量显示的内容-->
                <template #default="scope">
                    <span v-if="scope.row.scientific === 0" style="color: red">科研处未确认</span>
                    <span v-if="scope.row.scientific === 1" style="color: blue">科研处已确认</span>
                </template>
            </el-table-column>

            <el-table-column fixed="right" label="操作" width="120" v-if="this.user.role != 1">
                <template #default="scope">
                    <!--v-if="scope.row.officeDirector === 1"-->
                    <el-button link type="primary" size="small" @click="handleEdit(scope.row) " v-if="scope.row.officeDirector === 0" >确认</el-button>
                    <el-button link type="primary" size="small" @click="handleEdit(scope.row) " v-if="scope.row.officeDirector === 1" >取消确认</el-button>
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

            <!--      弹窗-->
            <el-dialog
                    v-model="dialogVisible"
                    title="提示"
                    width="30%"
            >
                <el-form :model="form" label-width="120px">
                    <el-form-item label="项目编号">
                        <el-input v-model="form.projectid" style="width: 70%" disabled />
                    </el-form-item>
                    <el-form-item label="工号">
                        <el-input v-model="form.userid" style="width: 70%" disabled/>
                    </el-form-item>
                    <el-form-item label="个人积分">
                        <el-input v-model="form.mark" style="width: 70%" disabled/>
                    </el-form-item>
                    <el-form-item label="操作员">
                        <el-input v-model="form.handler" style="width: 70%" disabled/>
                    </el-form-item>
                    <el-form-item label="项目进度">
                        <el-input v-model="form.projectProgress" style="width: 70%" disabled/>
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
        name: 'Group',
        components: {
        },
        data(){
            return{
                form:{},
                dialogVisible:false,
                search:'',
                user:{},
                projectid1 :'',
                currentPage:1,
                pageSize:10,
                total:0,
                tableData:[]
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

            if(this.user.role === 1){
                this.search = this.user.userid;

            }
            this.load();
  //          this.user=JSON.parse(user);
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
            add(){
                this.dialogVisible = true;
                this.form = {}
            },
            save(){

                    //把表单传给后台
                    //穿表单给后台
                    request.put("/group/confirm", this.form).then(res =>{//.then是es6里的语法
                        if(res.code === '0'){

                            this.$message({
                                type:"success",
                                message:"操作成功！"
                            })
                        }else{
                            this.$message({
                                type:"error",
                                message: res.msg
                            })
                        }
                        this.load();//刷新表格数据
                        this.dialogVisible = false;//关闭弹窗
                    })

            },
            handleEdit(row){

                this.form = JSON.parse(JSON.stringify(row));

                this.dialogVisible = true
            },
            handleDelete(row){
                this.form = JSON.parse(JSON.stringify(row));

                this.dialogVisible = true
            },
            handleSizeChange(pageSize){ //改变当前页的个数触发
                this.pageSize = pageSize;
                this.load()
            },
            handleCurrentChange(pageNum){ // 改变当前页码触发
                this.currentPage = pageNum
                this.load()
            }
        }
    }
</script>
