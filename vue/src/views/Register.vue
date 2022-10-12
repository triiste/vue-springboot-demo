<template>
    <div style="width: 100% ; height: 100vh; background-color:#2E8B57; overflow: hidden">
        <div style="width: 400px;margin: 100px auto">
            <div style="color: #cccccc; font-size: 30px;text-align: center;padding: 30px">欢迎注册</div>
            <el-form ref="form" :model="form" size="normal" :rules="rules">
                <el-form-item prop="userjobid">
                    <el-input :prefix-icon="Avatar" v-model="form.userjobid"/>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input :prefix-icon="Lock" v-model="form.password"  show-password />
                </el-form-item>
                <el-form-item prop="confirm">
                    <el-input :prefix-icon="Lock" v-model="form.confirm"  show-password />
                </el-form-item>
                <el-form-item>
                    <el-button style="width: 100%" type="primary" @click="register">注 册</el-button>
                </el-form-item>
            </el-form>
        </div>
        >

    </div>
</template>

<script>
    import request from "../utils/request";
    import {Avatar, Lock} from "@element-plus/icons-vue"
    export default {
        name: "Register",
        data() {
            return {
                form: {},
                 rules:{
                    userjobid: [
                        { required: true, message: '请输入用户ID', trigger: 'blur' },
                    ],
                     password: [
                         { required: true, message: '请输入密码', trigger: 'blur' },
                     ],
                     confirm: [
                         { required: true, message: '请确认密码', trigger: 'blur' },
                     ],
                 }
            }
        },
        methods:{
            register(){
                if(this.form.password != this.form.confirm){
                    this.$message({
                        type: "error",
                        message: "两次密码输入不一致"
                    })
                    return
                }
                this.$refs['form'].validate((valid) => {
                    if (valid) { //验证规则合法即只有用户名和密码都输入了才发请求
                        request.post("/user/register",this.form).then(res => {
                            if(res.code === '0'){
                                this.$message({
                                    type: "success",
                                    message: "注册成功"
                                })
                                this.$router.push("/login") //登录成功之后进行页面跳转 跳转到主页
                            }else{
                                this.$message({
                                    type: "error",
                                    message: res.msg
                                })
                            }
                        })
                    }
                })
            }
        },
        setup() {
            return {
                Avatar,
                Lock
            }
        },
    }
</script>

<style scoped>

</style>