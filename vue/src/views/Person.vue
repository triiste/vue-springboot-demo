<template>
    <div>
        <el-card style="width: 40%; margin: 10px">
            <el-form ref="form" :model="form" label-width="80px">
                <el-form-item style="text-align: center" label-width="0">

                </el-form-item>
                <el-form-item label="ID">
                    <el-input v-model="form.userid" disabled></el-input>
                </el-form-item>
                <el-form-item label="工号">
                    <el-input v-model="form.userjobid" disabled></el-input>
                </el-form-item>
                <el-form-item label="用户名">
                    <el-input v-model="form.username" disabled></el-input>
                </el-form-item>
                <el-form-item label="电话" >
                    <el-input v-model="form.telephone" disabled></el-input>
                </el-form-item>
                <el-form-item label="年龄">
                    <el-input v-model="form.age" disabled></el-input>
                </el-form-item>
                <el-form-item label="性别">
                <el-input v-model="form.sex" disabled></el-input>
            </el-form-item>
                <el-form-item label="地址">
                    <el-input v-model="form.address" disabled></el-input>
                </el-form-item>
                <el-form-item label="角色">
                    <el-input v-model="form.role" disabled></el-input>
                </el-form-item>
                <el-form-item label="职位">
                    <el-input v-model="form.position" disabled></el-input>
                </el-form-item>
                <el-form-item label="部门">
                    <el-input v-model="form.permission" disabled></el-input>
                </el-form-item>
                <el-form-item label="积分">
                <el-input v-model="form.mark" disabled></el-input>
            </el-form-item>
            </el-form>
        </el-card>

    </div>
</template>

<script>
    import request from "@/utils/request";
    export default {
        name: "Person",
        data() {
            return {
                form: {},
                project:'哈喽',
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
