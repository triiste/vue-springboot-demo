<template>
    <div style="padding: 10px">
        <!--    功能区域-->
        <!--<div style="margin: 10px 0" v-if="this.user.role != 1 && this.user.role != 2">-->
        <div style="margin: 10px 0">
            <el-button type="primary" @click="add" v-if="this.user.role === 2 || this.user.role === 4">新增</el-button>
        </div>
        <!--    搜索区域-->
        <!--<div style="margin: 10px 0" v-if="this.user.role != 2 && this.user.role != 1">-->
        <div style="margin: 10px 0">
            <el-input
                    style="width: 20%"
                    v-model="search"
                    size="large"
                    placeholder="请输入本室ID或者室主任工号"
                    clearable></el-input>
            <el-button type="primary" style="margin-left: 5px" @click="load">
                <span>查询</span>
            </el-button>
        </div>
        <el-table :data="tableData" stripe border style="width: 100%">
            <!--<template>-->
            <el-table-column prop="id" label="ID" sortable></el-table-column>
            <!--<el-table-column prop="officeID" label="本室编号" sortable></el-table-column>-->
            <el-table-column prop="officename" label="本室名称" sortable></el-table-column>
            <el-table-column prop="officeuserid" label="室主任id"></el-table-column>
            <el-table-column prop="username" label="室主任名称"></el-table-column>
            <el-table-column fixed="right" label="操作" width="120"  >
                <template #default="scope">
                    <el-button type="primary" size="small" @click="details(scope.row)">成员详情</el-button>
                    <el-button link type="primary" size="small" @click="handleEdit(scope.row)"  v-if="this.user.role ===2 ">编辑</el-button>
                    <el-popconfirm title="确认删除吗？" @confirm="handleDelete(scope.row.projectId)" >
                        <template #reference>
                            <el-button link type="danger" size="small" v-if="this.user.role ===2 ">删除</el-button>
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
                    <el-form-item label="本室编号">
                        <el-input v-model="form.id" style="width: 70%"></el-input>
                    </el-form-item>

                    <el-form-item label="本室名称" >
                        <el-input v-model="form.officename" style="width: 70%" ></el-input>
                    </el-form-item>
                    <el-form-item label="室主任ID" >
                        <el-input v-model="form.officeuserid" style="width: 70%" ></el-input>
                    </el-form-item>
                    <el-form-item label="室主任名称">
                        <el-input v-model="form.username" style="width: 70%"></el-input>
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



    import {Location, Setting, Menu, Document, User, Search} from '@element-plus/icons'
    import request from "@/utils/request";

    export default {
        name: 'Project',
        components: {
            Location, Setting, Menu, Document, User, Search
        },
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
                flag:0,
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
            this.load()
        },
        methods: {
            load() {
                request.get("/office/test", {
                     params: {
                        pageNum: this.currentPage,
                        pageSize: this.pageSize,
                     //     // search: this.search
                     }
                }).then(res => {
                  this.tableData=res.data;
                  //先全部查出来再去限制共几条是有问题的
                  this.total = res.total;
                })
            },
            add() {
                this.dialogVisible = true;
                this.form = {}
            },
            save() {
                if (this.form.id) {
                    request.put("/office", this.form).then(res => {//.then是es6里的语法
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
                        this.dialogVisible = false;//关闭新增弹窗
                        this.load();//刷新表格数据
                        // this.dialogVisible = false;//关闭新增弹窗

                    })
                } else {//插入
                    request.post("/office", this.form).then(res => {//.then是es6里的语法
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
                        this.dialogVisible = false;//关闭新增弹窗
                    })
                }

            },

            //编辑权限
            handleEdit(row) {
                this.form = JSON.parse(JSON.stringify(row));
                this.dialogVisible = true //关闭编辑弹窗
            },

            //成员详情
            details(row) {
                this.$router.push({
                    path: '/oneofficegroup',
                    query: {
                        message: row.id,
                        asd1:row.officeuserid,
                    }
                })
            },

            handleDelete(id) {
                console.log(id)
                request.delete("/project/" + id).then(res => {
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
                this.currentPage = pageNum;
                this.load()
            }
        }
    }
</script>
