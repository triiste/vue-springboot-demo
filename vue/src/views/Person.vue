<template>
    <div>
        <el-card style="width: 40%; margin: 10px">

            <el-form ref="form" :model="form" label-width="100px"  disabled>
                <el-form-item style="text-align: center" label-width="0"  >

                </el-form-item>
                <el-form-item label="用户工号">
                    <el-input v-model="form.userid" ></el-input>
                </el-form-item>
                <!--<el-form-item label="工号">-->
                    <!--<el-input v-model="form.userjobid" ></el-input>-->
                <!--</el-form-item>-->
                <el-form-item label="用户名">
                    <el-input v-model="form.username" ></el-input>
                </el-form-item>
                <el-form-item label="电话" >
                    <el-input v-model="form.telephone" ></el-input>
                </el-form-item>
                <el-form-item label="年龄">
                    <el-input v-model="form.age" ></el-input>
                </el-form-item>
                <el-form-item label="性别">
                <el-input v-model="form.sex" ></el-input>
            </el-form-item>
                <el-form-item label="地址">
                    <el-input v-model="form.address" ></el-input>
                </el-form-item>
                <el-form-item label="职称">
                    <el-input v-model="form.position" ></el-input>
                </el-form-item>
                <el-form-item label="单位">
                    <el-input v-model="form.permission" ></el-input>
                </el-form-item>
                <el-form-item label="军衔">
                    <el-input  v-model="form.martialStatus" />
                </el-form-item>
                <el-form-item label="室奖励积分">
                    <el-input  v-model="form.mark" />
                </el-form-item>
                <el-form-item label="所奖励积分">
                <el-input  v-model="form.placeMark"></el-input>
                </el-form-item>
                <!--<el-form-item label="研究室奖励积分" v-if="this.user.role === 3">-->
                    <!--<el-input v-model="form.mark" style="width: 70%" ></el-input>-->
                <!--</el-form-item>-->
                <!--<el-form-item label="所领导奖励积分" v-if="this.user.role === 4">-->
                    <!--<el-input  v-model="form.placeMark" style="width: 70%"></el-input>-->
                <!--</el-form-item>-->
                <!--<el-form-item label="积分">-->
                <!--<el-input v-model="form.mark" ></el-input>-->
            <!--</el-form-item>-->

            </el-form>
            <el-button type="primary" @click="Edit" style="margin-left: 350px">修改密码</el-button>
        </el-card>

        <el-dialog
                v-model="dialogVisible"
                title="提示"
                width="30%"
        >
            <el-form :model="form1" label-width="120px" >
                <!--<el-form-item label="用户名">-->
                    <!--<el-input v-model="form.username" style="width: 70%"/>-->
                <!--</el-form-item>-->
                <!--<el-form-item label="电话">-->
                    <!--<el-input v-model="form.telephone" style="width: 70%"/>-->
                <!--</el-form-item>-->
                <!--<el-form-item label="年龄">-->
                    <!--<el-input v-model="form.age" style="width: 70%"/>-->
                <!--</el-form-item>-->
                <!--<el-form-item label="性别">-->
                    <!--<el-radio-group v-model="form.sex">-->
                        <!--<el-radio label="男" size="large">男</el-radio>-->
                        <!--<el-radio label="女" size="large">女</el-radio>-->
                    <!--</el-radio-group>-->
                <!--</el-form-item>-->
                <!--<el-form-item label="地址">-->
                    <!--<el-input type="textarea" v-model="form.address" style="width: 70%"/>-->
                <!--</el-form-item>-->
                <el-form-item label="请输入原密码">
                    <el-input type="password" v-model="form1.password0" style="width: 70%" show-password/>
                </el-form-item>
                <el-form-item label="新密码">
                    <el-input type="password" v-model="form1.password1" style="width: 70%" show-password/>
                </el-form-item>
                <el-form-item label="请确认密码">
                    <el-input type="password" v-model="form1.password2" style="width: 70%" show-password/>
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
                <!--<el-form-item label="职位">-->
                    <!--<el-input v-model="form.position" style="width: 70%"/>-->
                <!--</el-form-item>-->
                <!--<el-form-item label="部门">-->
                    <!--<el-input type="textarea" v-model="form.permission" style="width: 70%"/>-->
                <!--</el-form-item>-->
                <!--<el-form-item label="积分">-->
                <!--<el-input type="number" v-model="form.mark" style="width: 70%"/>-->
                <!--</el-form-item>-->
            </el-form>

            <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="save">保存</el-button>
        <el-button @click="dialogVisible = false">取消</el-button>
      </span>
            </template>
        </el-dialog>
    </div>
</template>

<script>
    import request from "@/utils/request";
    export default {
        name: "Person",
        data() {
            return {
                form: {},
                form1:{},

                dialogVisible: false,

                // role1:"普通员工",
                // role2:"项目组长",
                // role3:"室主任",
                // role4:"科研处",
                // role5:"总管理员",
            }
        },
        created() {

            let userStr = localStorage.getItem("user") || "{}";
            this.form =JSON.parse(userStr); //将缓存的字符串存起来
            //请求服务端，确认当前登录用户的合法信息，服务器传不过来
            //下面这是一种交互方式，通过前台的缓存数据传来后台的form表单
            request.get("/user/" + this.form.userid).then(res => {
                if(res.code === '0'){
                    this.form = res.data
                    console.log(this.form)
                }
            })

            // //去项目数据库找该人负责的项目
            // request.get("/project/" + this.form.userid).then(res => {
            //     if(res.code === '0'){
            //         project = res.data.projectName;
            //     }
            // })
        },
        methods: {
            Edit(){
                    this.dialogVisible = true;
            },
            save(){

                if(this.form1.password1 !==this.form1.password2)
                {
                    this.$message({
                        type: "error",
                        message: "两次输入密码不一致！！！"
                    })
                }

                else{
                    request.get("/user/edit",{
                        params: {
                            userjobid:this.form.userid,
                            password0:this.form1.password0,
                            password1:this.form1.password1,

                        }
                    }).then(res => {
                        if (res.code === '0') {
                            this.$message({
                                type: "success",
                                message: "修改成功！！！"
                            });
                        }else{
                            this.$message({
                                type: "error",
                                message: res.msg
                            })
                        }
                    });

                    }
                this.dialogVisible = false;
                }
            }

    }
</script>
<style>
    .avatar-uploader .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }
    .avatar-uploader .el-upload:hover {
        border-color: #409EFF;
    }
    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 120px;
        height: 120px;
        line-height: 120px;
        text-align: center;
    }
    .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }
</style>
