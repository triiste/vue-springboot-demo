<template>
    <div style="padding: 10px">
        <!--    功能区域-->
        <!--<div style="margin: 10px 0" v-if="this.user.role != 1 && this.user.role != 2">-->
        <div style="margin: 10px 0">
            <el-button type="primary" @click="add">新增</el-button>
        </div>
        <!--    搜索区域-->
        <!--<div style="margin: 10px 0" v-if="this.user.role != 2 && this.user.role != 1">-->
        <div style="margin: 10px 0">
            <el-input
                    style="width: 20%"
                    v-model="search"
                    size="large"
                    placeholder="请输入项目ID或者组长工号"
                    clearable
            />
            <el-button type="primary" style="margin-left: 5px" @click="load">
                <span>查询</span>
                <!--<el-icon><Search /></el-icon>-->
            </el-button>
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

            <el-table-column fixed="right" label="操作" width="120" v-if="this.user.role != 1">
                <template #default="scope">
                    <!--v-if="this.user.userid === JSON.parse(JSON.stringify(row)).ProjectgroupId"-->
                    <!--v-if="this.user.userid === form.projectgroupId"-->
                    <el-button type="primary" size="small" @click="details(scope.row)"
                    >成员详情
                    </el-button>
                    <el-button link type="primary" size="small" @click="handleEdit(scope.row)"
                    >编辑
                    </el-button>
                    <el-popconfirm title="确认删除吗？" @confirm="handleDelete(scope.row.projectId)">
                        <template #reference>
                            <el-button link type="danger" size="small"

                            >删除
                            </el-button>
                        </template>
                    </el-popconfirm>


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
                    title="提示"
                    width="30%"
            >
                <el-form :model="form" label-width="120px">
                    <el-form-item label="项目编号">
                        <el-input v-model="form.projectItemid" style="width: 70%"/>
                    </el-form-item>
                    <el-form-item label="项目名称">
                        <el-input v-model="form.projectName" style="width: 70%"/>
                    </el-form-item>
                    <el-form-item label="项目组号">
                        <el-input v-model="form.groupId" style="width: 70%"/>
                    </el-form-item>
                    <el-form-item label="项目组长ID">
                        <el-input v-model="form.projectgroupId" style="width: 70%"/>
                    </el-form-item>
                    <el-form-item label="研究内容">
                        <el-input v-model="form.projectDetial" style="width: 70%"/>
                    </el-form-item>
                    <el-form-item label="研究周期">
                        <el-input v-model="form.projectCycle" style="width: 70%"/>
                    </el-form-item>
                    <el-form-item label="项目进度">
                        <el-input v-model="form.projectProgress" style="width: 70%"/>
                    </el-form-item>
                    <el-form-item label="成果形式">
                        <el-input v-model="form.projectProduct" style="width: 70%"/>
                    </el-form-item>
                    <el-form-item label="研究类别">
                        <!--                        <el-input v-model="form.projectType" style="width: 70%"></el-input>-->
                        <el-select v-model="form.projectType" style="width: 70%" placeholder="请选择">
                            <el-option
                                    v-for="item in type"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.label"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="研究级别">
                        <!--                        <el-input v-model="form.projectLevel" style="width: 70%"></el-input>-->
                        <el-select v-model="form.projectLevel" style="width: 70%" placeholder="请选择">
                            <el-option
                                    v-for="item in level"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.label"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="项目积分">
                        <el-input v-model="form.projectMark" style="width: 70%"/>
                    </el-form-item>
                    <el-form-item label="奖励积分">
                        <el-input v-model="form.projectReward" style="width: 70%"/>
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
                    <el-form-item label="项目编号">
                        <el-input v-model="form.projectItemid" disabled="true" style="width: 70%"></el-input>
                    </el-form-item>
                    <el-form-item label="项目名称">
                        <el-input v-model="form.projectName" disabled="true" style="width: 70%"></el-input>
                    </el-form-item>
                    <el-form-item label="项目组长ID">
                        <el-input v-model="form.projectgroupId" disabled="true" style="width: 70%"></el-input>
                    </el-form-item>
                    <el-form-item label="项目进度">
                        <el-input v-model="form.projectProgress" style="width: 70%"></el-input>
                    </el-form-item>
                    <el-form-item label="研究类别">
                        <el-input v-model="form.projectType" disabled="true" style="width: 70%"></el-input>
                    </el-form-item>
                    <el-form-item label="研究级别">
                        <el-input v-model="form.projectLevel" disabled="true" style="width: 70%"></el-input>
                    </el-form-item>
                    <el-form-item label="项目总积分">
                        <el-input v-model="form.projectMark" disabled="true" style="width: 70%"></el-input>
                    </el-form-item>
                    <el-form-item label="第一阶段积分比">
                        <el-input v-model="form.first" style="width: 70%"></el-input>%
                    </el-form-item>
                    <!--                    <el-form-item label="第一阶段积分">-->
                    <!--                        <el-input v-model="form.firstMark" style="width: 70%"></el-input>-->
                    <!--                    </el-form-item>-->
                    <el-form-item label="第二阶段积分比">
                        <el-input v-model="form.second" style="width: 70%"></el-input>%
                    </el-form-item>
                    <el-form-item label="第三阶段积分比">
                        <el-input v-model="form.three" style="width: 70%"></el-input>%
                    </el-form-item>
                    <el-form-item label="附加分">
                        <el-input v-model="form.projectReward" style="width: 70%"></el-input>
                    </el-form-item>
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
                dialog: false,
                search: '',
                user: {},
                //  project:{},
                currentPage: 1,
                pageSize: 10,
                total: 0,
                tableData: [],
                type: [{  //项目类别
                    label: '计划科研项目'
                }, {
                    label: '专项任务'
                }],
                level: [{  //项目级别
                    label: '重大'
                }, {
                    label: '重点'
                }],
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
                request.get("/project", {
                    params: {
                        pageNum: this.currentPage,
                        pageSize: this.pageSize,
                        search: this.search
                    }
                }).then(res => {
                    console.log(res);
                    this.tableData = res.data.records;
                    this.total = res.data.total;
                })
            },

            add() {
                this.dialogVisible = true;
                this.form = {}
            },
            save() {
                if (this.form.projectId) {
                    this.form.firstMark=this.form.first*this.form.projectMark*0.01;
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
                        this.dialog = false;//关闭编辑弹窗
                        this.load();//刷新表格数据
                        // this.dialogVisible = false;//关闭新增弹窗

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
                        this.dialogVisible = false;//关闭新增弹窗
                    })
                }

            },
            //编辑权限
            handleEdit(row) {
                this.form = JSON.parse(JSON.stringify(row));

                this.dialog = true


            },
            details(row) {
                this.$router.push({
                    path: '/oneitemgroup',
                    query: {
                        message: row.projectId,
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
