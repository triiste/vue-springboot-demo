<template>
    <div style="width: 100%;height: 100vh;background-color:#2E8B57;overflow: hidden">
        <div style="width: 400px; margin: 150px auto">
            <div style="font-size: 30px; text-align: center; padding: 30px 0; color: #cccccc">欢迎登录</div>
            <el-form ref="form" :model="form" size="large" :rules="rules">
                <el-form-item :model="form" prop="userjobid"
                >
                    <el-input :prefix-icon="Avatar" v-model="form.userjobid" placeholder="请输入工号"></el-input>
                </el-form-item>
                <el-form-item :model="form" prop="password">
                    <el-input :prefix-icon="Lock" v-model="form.password" show-password placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-form-item :model="form">
                    <!--lable标签是表示值的-->
                    <el-radio v-model="form.role" :label="1" style="color: white">普通员工</el-radio>
                    <el-radio v-model="form.role" :label="2" style="color: white">总管理</el-radio>
                    <el-radio v-model="form.role" :label="3" style="color: white">室主任</el-radio>
                    <el-radio v-model="form.role" :label="4" style="color: white">所领导</el-radio>
                    <!--<el-radio v-model="form.role" :label="3" style="color: white">室主任</el-radio>-->
                    <!--<el-radio v-model="form.role" :label="4" style="color: white">科研处</el-radio>-->
                    <!--<el-radio v-model="form.role" :label="5" style="color: white">总后台管理员</el-radio>-->
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="login">登 录</el-button>
                    <!--<el-button type="primary" @click="register">注册</el-button>-->
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import request from "@/utils/request";
    import {Avatar, Lock} from "@element-plus/icons-vue"

    export default {
        name: "Login",
        data() {
            return {
                form: {},
                rules: {
                    userjobid: [
                        {required: true, message: '请输入用户名', trigger: 'blur'},
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                    ],
                },
            }
        },
        setup() {
            return {
                Avatar,
                Lock
            }
        },
        created() {
            sessionStorage.removeItem("user");
        },
        methods: {
            login() {
                request.post("/user/login", this.form).then(res => {
                    // console.log("/user/login");
                    if (res.code === '0') {
                        this.$message({
                            type: "success",
                            message: "登录成功"
                        });
                        console.log("daole");
                        localStorage.setItem("user", JSON.stringify(res.data));
                        //  if(this.form.role === 1)
                        //     this.$router.push("/person");  //登录成功之后进行页面的跳转，跳转到主页
                        // else
                        if(this.form.role === 1)  //普通员工项目组长
                            this.$router.push("/hostproject") ; //登录成功之后进行页面的跳转，跳转到主页
                        else if(this.form.role ===3)  //室主任
                            this.$router.push("/officeperson") ; //登录成功之后进行页面的跳转，跳转到主页
                        else if(this.form.role ===2)  //科研处
                            this.$router.push("/project");
                        else if(this.form.role ===4)  //所长
                            this.$router.push("/project");

                    } else {
                        this.$message({
                            type: "error",
                            message: res.msg
                        })
                    }
                })
            },
            register() {
                this.$router.push("/register")  //登录成功之后进行页面的跳转，跳转到主页
            }

        }
    }
</script>

<style scoped>

</style>