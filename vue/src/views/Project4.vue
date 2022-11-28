<template>
    <div style="padding: 10px">
        <!--    功能区域-->
        <!--<div style="margin: 10px 0" v-if="this.user.role != 1 && this.user.role != 2">-->
        <div style="margin: 10px 0">
            <el-button type="primary" @click="add" v-if="this.user.role !== 3 && this.user.role !== 4">新增</el-button>
        </div>
        <!--    搜索区域-->
        <!--<div style="margin: 10px 0" v-if="this.user.role != 2 && this.user.role != 1">-->
        <div style="margin: 10px 0">
            <el-input
                    style="width: 20%"
                    v-model="search"
                    size="large"
                    placeholder="请输入项目ID或者组长工号"
                    clearable></el-input>
            <el-button type="primary" style="margin-left: 5px" @click="load">
                <span>查询</span>
            </el-button>
        </div>
        <el-table :data="tableData" stripe border style="width: 100%">
            <!--<template>-->
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
            <el-table-column fixed="right" label="操作" width="120" v-if="this.user.role !== 1">
                <template #default="scope">
                    <el-button type="primary" size="small" @click="details(scope.row)" >成员详情</el-button>
                    <el-button link type="primary" size="small" @click="handleEdit(scope.row)" v-if="this.user.role === 2">编辑</el-button>
                    <el-button link type="primary" size="small" @click="handleEdit1(scope.row)" v-if="scope.row.projectMark === 0">编辑积分</el-button>
                    <el-popconfirm title="确认删除吗？" @confirm="handleDelete(scope.row.projectId)">
                        <template #reference>
                            <el-button link type="danger" size="small" v-if="this.user.role === 2">删除</el-button>
                        </template>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>


        <div style="margin: 10px 0">
            <el-pagination
                    v-model:currentPage="currentPage"
                    :page-sizes="[5, 10, 20,total]"
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
                    <el-form-item label="立项时间">
                        <div class="demo-date-picker"    style="width: 70%">
                            <div class="block">
                                <el-date-picker
                                        v-model="form.date"
                                        type="date"
                                        placeholder="请选择日期"
                                        :size="size"

                                />
                            </div>
                        </div>

                    </el-form-item>
                    <el-form-item label="项目编号">
                        <el-input v-model="form.projectId"  style="width: 70%"
                                  maxlength="9"
                                  show-word-limit></el-input>
                    </el-form-item>
                    <el-form-item label="项目名称">
                        <el-input v-model="form.projectName" style="width: 70%"></el-input>
                    </el-form-item>
                    <!--                    <el-form-item label="项目组号">-->
                    <!--                        <el-input v-model="form.groupId" style="width: 70%"/>-->
                    <!--                    </el-form-item>-->
                    <el-form-item label="项目组长工号">
                        <el-input v-model="form.projectgroupId" style="width: 70%"></el-input>
                    </el-form-item>
                    <!--                    <el-form-item label="研究内容">-->
                    <!--                        <el-input v-model="form.projectDetial" style="width: 70%"/>-->
                    <!--                    </el-form-item>-->
                    <!--                    <el-form-item label="研究周期">-->
                    <!--                        <el-input v-model="form.projectCycle" style="width: 70%"/>-->
                    <!--                    </el-form-item>-->
                    <el-form-item label="项目进度">
                        <el-input v-model="form.projectProgress" style="width: 70%"></el-input>
                    </el-form-item>
                    <!--                    <el-form-item label="成果形式">-->
                    <!--                        <el-input v-model="form.projectProduct" style="width: 70%"/>-->
                    <!--                    </el-form-item>-->
                    <el-form-item label="研究类别">
                        <el-cascader :options="type" v-model="form.projectType"  placeholder="请选泽" style="width: 70%" clearable>

                        </el-cascader>

                    </el-form-item>

                </el-form>
                <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="save1">确定</el-button>
        <el-button @click="dialogVisible = false">取消</el-button>
      </span>
                </template>


                <!--      编辑弹窗-->
            </el-dialog>
            <el-dialog
                    v-model="dialog"
                    title="提示"
                    width="30%"
            >
                <el-form :model="form" label-width="120px">
                    <el-form-item label="立项时间">
                        <el-input v-model="form.date"  style="width: 70%" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="项目编号">
                        <el-input v-model="form.projectId"  style="width: 70%" disabled></el-input>
                    </el-form-item>
                    <!--<el-form-item label="项目编号">-->
                    <!--<el-input v-model="form.projectItemid"  style="width: 70%" disabled></el-input>-->
                    <!--</el-form-item>-->
                    <el-form-item label="项目名称">
                        <el-input v-model="form.projectName" style="width: 70%" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="项目组长工号">
                        <el-input v-model="form.projectgroupId" style="width: 70%" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="项目进度">
                        <el-input v-model="form.projectProgress" style="width: 70%" ></el-input>
                    </el-form-item>
                    <!--<el-form-item label="项目总积分">-->
                    <!--<el-input v-model="form.projectMark"  style="width: 70%"></el-input>-->
                    <!--</el-form-item>-->
                    <el-form-item label="第一阶段积分比">
                        <el-input-number v-model="form.first"  :min="0" :max="100" style="width: 70%" ></el-input-number>%
                        <!--<el-input v-model="form.first" style="width: 70%" @blur="check(form.first)"></el-input>-->
                    </el-form-item>
                    <el-form-item label="第二阶段积分比">
                        <el-input-number v-model="form.second"   :min="0" :max="100" style="width: 70%"></el-input-number>%
                    </el-form-item>
                    <el-form-item label="第三阶段积分比">
                        <el-input-number v-model="form.three"   :min="0" :max="100" style="width: 70%"></el-input-number>%
                    </el-form-item>
                    <!--<el-form-item label="附加分">-->
                    <!--<el-input v-model="form.projectReward" style="width: 70%"></el-input>-->
                    <!--</el-form-item>-->
                </el-form>
                <template #footer>
<span class="dialog-footer">
  <el-button type="primary" @click="save">确定</el-button>
  <el-button @click="dialog = false">取消</el-button>
</span>
                </template>
            </el-dialog>
            <el-dialog
                    v-model=" dialog1"
                    title="只能修改一次积分！！"
                    width="30%"
            >
                <el-form :model="form" label-width="120px">
                    <el-form-item label="项目总积分">
                        <el-input v-model="form.projectMark" style="width: 70%"></el-input>
                    </el-form-item>
                </el-form>
                <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="save">确定</el-button>
        <el-button @click="dialog1 = false">取消</el-button>
      </span>
                </template>


                <!--      编辑弹窗-->
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
                dialog1:false,
                search: '',
                user: {},
                count:0,
                //  project:{},
                currentPage: 1,
                pageSize: 10,
                total: 0,
                tableData: [],
                gridData: [
                    {
                        details: '',
                    },
                ],
                type: [
                    {  //项目类别
                        // value: '计划科研项目',
                        label: '计划科研项目',
                        children: [{
                            value: '1_1',
                            label: '重大'
                        }, {
                            value: '1_2',
                            label: '重点'
                        }, {
                            value: '1_3',
                            label: '一般'
                        }, {
                            value:'1_4',
                            label: '其他'
                        }]
                    }, {
                        label: '专项任务',
                        children: [{
                            value: '2_1',
                            label: '重大'
                        }, {
                            value: '2_2',
                            label: '重点'
                        }, {
                            value: '2_3',
                            label: '一般'
                        }, {
                            value: '2_4',
                            label: '其他'
                        }]
                    }, {
                        label: '临时性研究任务',
                        children: [{
                            label: '一般',
                            children: [{
                                value: '3_1_1',
                                label: '研究周期7-12个月'
                            },{
                                value: '3_1_2',
                                label: '研究周期4-6个月'
                            },{
                                value: '3_1_3',
                                label: '研究周期1-3个月'
                            },{
                                value: '3_1_4',
                                label: '研究周期1个月以内'
                            }]
                        }, {
                            value: '3_2',
                            label: '其他'
                        }
                        ]
                    }, {
                        label: '服务支撑业务',
                        children: [
                            {   label:'支撑机关、服务部队任务',
                                children:[
                                    {
                                        label: '到各级机关和基层部队授课',
                                        children: [{
                                            value: '4_1_1',
                                            label: '战区级及以上'
                                        },{ value: '4_1_2',
                                            label: '军级以下'
                                        }]
                                    },

                                    {
                                        value: '4_2',
                                        label: '为部队提供技术服务',
                                    },{

                                        label: '参加院以上机关集中攻关/帮助工作',
                                        children: [{
                                            value: '4_3_1',
                                            label: '1月(含)以内'
                                        },{
                                            value: '4_3_2',
                                            label: '1-3个月'
                                        }, {
                                            value: '4_3_3',
                                            label: '4-6个月'
                                        },
                                        ]
                                    },
                                    { label: '支撑机关职能办公室',
                                        children: [{
                                            value: '4_4_1',
                                            label: '牵头负责人（每年）'
                                        },{
                                            value: '4_4_2',
                                            label: '其他不固定参与人员（每次）'
                                        }]
                                    },
                                    {
                                        label: '为军事行动、战区级以上重大演训任务和重大活动提供提供技术保障',
                                        children: [{
                                            value: '4_5_1',
                                            label: '单次任务15天以下'
                                        },{
                                            value: '4_5_2',
                                            label: '单次任务16-30天'
                                        },
                                            {
                                                value: '4_5_3',
                                                label: '单次任务31-60天'
                                            },
                                            {
                                                value: '4_5_4',
                                                label: '单次任务61-90天'
                                            },
                                            {   value: '4_5_5',
                                                label: '单次任务90天以上'
                                            },
                                        ]
                                    },
                                ]
                            },
                            {
                                label:'支撑研究所建设任务',
                                children:[
                                    {
                                        value: '4_6',
                                        label: '实验室建设与运维',
                                    },
                                    {
                                        label: '导师带教',
                                        children: [{
                                            value: '4_7_1',
                                            label: '博士（后）'
                                        },{
                                            value: '4_7_2',
                                            label: '硕士'
                                        },
                                            {
                                                value: '4_7_3',
                                                label: '科技英才等青年骨干带教'
                                            },
                                            {   value: '4_7_4',
                                                label: '其他带教'
                                            }
                                        ]
                                    },
                                    {    value: '4_8',
                                        label: '建设发展规划论证',
                                    },
                                    {   value: '4_9',
                                        label: '技术咨询-立项咨询（单次）',
                                    },
                                    {
                                        label: '技术咨询-成果审查',
                                        children: [{
                                            value: '4_10_1',
                                            label: '重大/重点项目单次审查'
                                        },{
                                            value: '4_10_2',
                                            label: '一般项目成果单次审查'
                                        },
                                            {   value: '4_10_3',
                                                label: '所内合同审查'
                                            },
                                        ]
                                    },
                                    {
                                        label: '技术咨询-项目指导专家',
                                        children: [
                                            {
                                                value: '4_11_1',
                                                label: '重大项目（专项任务）'
                                            },
                                            {
                                                value: '4_11_2',
                                                label: '重点项目（专项任务）'
                                            },
                                        ]
                                    },
                                    {
                                        label: '所机关锻炼',
                                        children: [
                                            {
                                                value: '4_12_1',
                                                label: '1月(含)以内'
                                            },
                                            {
                                                value: '4_12_2',
                                                label: '1-3个月'
                                            },
                                            {
                                                value: '4_12_3',
                                                label: '4-6个月'
                                            },
                                            {
                                                value: '4_12_4',
                                                label: '每增加一个月增加'
                                            },
                                        ]
                                    },
                                    {
                                        label: '专项报告材料',
                                        children: [
                                            {
                                                value: '4_13_1',
                                                label: '被所领导采纳'
                                            },
                                            {
                                                value: '4_13_2',
                                                label: '被所机关采纳'
                                            },
                                        ]
                                    },
                                    {
                                        label: '科研保障支撑',
                                        children: [
                                            {
                                                value: '4_14_1',
                                                label: '重大专项科研活动'
                                            },
                                            {
                                                value: '4_14_2',
                                                label: '日常科研保障服务'
                                            },
                                        ]
                                    },
                                    {
                                        label: '科研管理',
                                        children: [

                                            {
                                                value: '4_15_1',
                                                label: '实验室主任'
                                            },
                                            {
                                                value: '4_15_2',
                                                label: '研究室主任'
                                            },
                                            {
                                                value: '4_15_3',
                                                label: '研究室副主任'
                                            },
                                            {
                                                value: '4_15_4',
                                                label: '专业组长'
                                            },
                                        ]

                                    },
                                    {
                                        value: '4_16',
                                        label:"其他"
                                    }
                                ]
                            },
                        ]

                    }],
                /* level: [{  //项目级别
                     label: '重大'
                 }, {
                     label: '重点'
                 },{
                     label: '一般'
                 }],*/
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
                request.get("/project/p4", {
                    params: {
                        pageNum: this.currentPage,
                        pageSize: this.pageSize,
                        search: this.search
                    }
                }).then(res => {
                    // console.log("看看此时的table"+res);

                    this.tableData = res.data.records;
                    this.total = res.data.total;
                    // console.log("看看此时的table"+this.tableData[0].projectId);
                    for(var i=0;i<this.tableData.length;i++){

                        // this.tableData[i].date = new Date(parseInt(res.data.reccords[i].date)).toLocaleString();
                        // this.tableData[i].date=this.tableData[i].date.substr(0.10);
                        console.log(this.tableData[i].date.substring(0,10));
                        this.tableData[i].date=this.tableData[i].date.substring(0,10);
                        this.tableData[i].sumTotal=this.tableData[i].firstMark+this.tableData[i].secondMark+this.tableData[i].threeMark;
                    }

                })
            },

            add() {
                this.dialogVisible = true;
                this.form = {}
            },
            save1(){
                // console.log(this.form)
                // console.log("看看新增的啥东西"+this.form.date)
                if(this.form.date === null || typeof(this.form.date)==="undefined"){
                    this.$message({
                        type: "error",
                        message: "未输入立项时间！！！"
                    });
                    this.dialogVisible = false;//关闭新增弹窗
                }
                else if(this.form.projectId === null || typeof(this.form.projectId)==="undefined"){
                    this.$message({
                        type: "error",
                        message: "未输入项目编号！！！"
                    });
                    this.dialogVisible = false;//关闭新增弹窗
                }
                else if (this.form.projectType === null || typeof(this.form.projectType)==="undefined") {
                    this.$message({
                        type: "error",
                        message: "未选择研究类别"
                    });
                    this.dialogVisible = false;//关闭新增弹窗
                }
                //先看id是否存在
                else{
                    console.log(this.form.projectId);
                    request.get("/project/" + this.form.projectId).then(res => {
                        if (res.data === null) {
                            // console.log("不存在")
                            this.count=0;
                            for(var i in this.form.projectType){
                                this.count ++;
                            }
                            console.log("看看此时的级别"+this.form.projectType[this.count-1]);
                            var tmp=this.form.projectType[this.count-1];
                            this.form.projectType=tmp;
                            // console.log(typeof this.form.projectType);
                            // console.log("准备传的东西"+this.form.projectType);
                            request.post("/project", this.form).then(res => {//.then是es6里的语法

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
                        }else{
                            this.$message({
                                type: "error",
                                message: "该项目编号已存在，请重新输入！！！"
                            })
                            this.dialogVisible = false;//关闭弹窗
                        }
                    });
                }

                //

            },
            save() {
                this.form.firstMark=this.form.first*this.form.projectMark*0.01;
                this.form.secondMark=this.form.second*this.form.projectMark*0.01;
                this.form.threeMark=this.form.three*this.form.projectMark*0.01;

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
                    this.dialog1 = false;
                    this.load();//刷新表格数据
                    // this.dialogVisible = false;//关闭新增弹窗

                })

            },

            //编辑权限
            handleEdit(row) {
                this.form = JSON.parse(JSON.stringify(row));
                this.dialog = true //关闭编辑弹窗
            },
            handleEdit1(row) {
                this.form = JSON.parse(JSON.stringify(row));
                this.dialog1 = true //关闭编辑弹窗
            },

            //成员详情
            details(row) {
                this.$router.push({
                    path: '/oneitemgroup',
                    query: {
                        message: row.projectId,
                        asd1:row.firstMark,
                        asd2:row.secondMark,
                        asd3:row.threeMark
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

            handleDelete(id) {
                console.log(id)

                request.delete("/project/" + id).then(res => {
                    if (res === true) {
                        this.$message({
                            type: "success",
                            message: "删除成功！"
                        })
                    } else {
                        this.$message({
                            type: "error",
                            message: "本项目还有人员，不能删除！"
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
