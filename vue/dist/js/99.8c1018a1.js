"use strict";(self["webpackChunkspringboot_vue_demo2"]=self["webpackChunkspringboot_vue_demo2"]||[]).push([[99],{3099:function(e,a,l){l.r(a),l.d(a,{default:function(){return w}});var t=l(6252);const o={style:{padding:"10px"}},r={key:0,style:{margin:"10px 0"}},s=(0,t.Uk)("新增"),d=(0,t.Uk)("返回上一步"),i=(0,t.Uk)("成员详情"),m={style:{margin:"10px 0"}},p={class:"dialog-footer"},u=(0,t.Uk)("确定"),n=(0,t.Uk)("取消");function h(e,a,l,h,g,c){const f=(0,t.up)("el-button"),b=(0,t.up)("el-table-column"),w=(0,t.up)("el-table"),y=(0,t.up)("el-pagination"),W=(0,t.up)("el-input"),V=(0,t.up)("el-form-item"),k=(0,t.up)("el-form"),j=(0,t.up)("el-dialog");return(0,t.wg)(),(0,t.iD)("div",o,[1!=this.user.role?((0,t.wg)(),(0,t.iD)("div",r,[1===this.user.role?((0,t.wg)(),(0,t.j4)(f,{key:0,type:"primary",onClick:c.add},{default:(0,t.w5)((()=>[s])),_:1},8,["onClick"])):(0,t.kq)("",!0),(0,t.Wm)(f,{type:"primary",onClick:c.returnhere},{default:(0,t.w5)((()=>[d])),_:1},8,["onClick"])])):(0,t.kq)("",!0),(0,t.Wm)(w,{data:g.tableData,stripe:"",border:"",style:{width:"100%"}},{default:(0,t.w5)((()=>[(0,t.Wm)(b,{prop:"projectId",label:"ID",sortable:""}),(0,t.Wm)(b,{prop:"projectName",label:"项目名"}),(0,t.Wm)(b,{prop:"projectgroupId",label:"项目组长ID"}),(0,t.Wm)(b,{prop:"projectProgress",label:"项目进度"}),(0,t.Wm)(b,{prop:"projectType",label:"研究类别"}),(0,t.Wm)(b,{prop:"projectLevel",label:"研究级别"}),(0,t.Wm)(b,{prop:"projectMark",label:"项目积分"}),(0,t.Wm)(b,{prop:"firstMark",label:"第一阶段积分"}),(0,t.Wm)(b,{prop:"secondMark",label:"第二阶段积分"}),(0,t.Wm)(b,{prop:"threeMark",label:"第三阶段积分"}),(0,t.Wm)(b,{prop:"projectReward",label:"附加分"}),(0,t.Wm)(b,{fixed:"right",label:"操作",width:"120"},{default:(0,t.w5)((e=>[(0,t.Wm)(f,{type:"primary",size:"small",onClick:a=>c.details(e.row)},{default:(0,t.w5)((()=>[i])),_:2},1032,["onClick"])])),_:1})])),_:1},8,["data"]),(0,t._)("div",m,[(0,t.Wm)(y,{currentPage:g.currentPage,"onUpdate:currentPage":a[0]||(a[0]=e=>g.currentPage=e),"page-sizes":[5,10,20],"page-size":g.pageSize,disabled:e.disabled,layout:"total, sizes, prev, pager, next, jumper",total:g.total,onSizeChange:c.handleSizeChange,onCurrentChange:c.handleCurrentChange},null,8,["currentPage","page-size","disabled","total","onSizeChange","onCurrentChange"]),(0,t.Wm)(j,{modelValue:g.dialogVisible,"onUpdate:modelValue":a[9]||(a[9]=e=>g.dialogVisible=e),title:"提示",width:"30%"},{footer:(0,t.w5)((()=>[(0,t._)("span",p,[(0,t.Wm)(f,{type:"primary",onClick:c.save},{default:(0,t.w5)((()=>[u])),_:1},8,["onClick"]),(0,t.Wm)(f,{onClick:a[8]||(a[8]=e=>g.dialogVisible=!1)},{default:(0,t.w5)((()=>[n])),_:1})])])),default:(0,t.w5)((()=>[(0,t.Wm)(k,{model:g.form,"label-width":"120px"},{default:(0,t.w5)((()=>[(0,t.Wm)(V,{label:"项目ID"},{default:(0,t.w5)((()=>[(0,t.Wm)(W,{modelValue:g.form.projectId,"onUpdate:modelValue":a[1]||(a[1]=e=>g.form.projectId=e),style:{width:"70%"},disabled:""},null,8,["modelValue"])])),_:1}),(0,t.Wm)(V,{label:"项目名称"},{default:(0,t.w5)((()=>[(0,t.Wm)(W,{modelValue:g.form.projectName,"onUpdate:modelValue":a[2]||(a[2]=e=>g.form.projectName=e),style:{width:"70%"},disabled:""},null,8,["modelValue"])])),_:1}),(0,t.Wm)(V,{label:"项目组长ID"},{default:(0,t.w5)((()=>[(0,t.Wm)(W,{modelValue:g.form.projectgroupId,"onUpdate:modelValue":a[3]||(a[3]=e=>g.form.projectgroupId=e),style:{width:"70%"},disabled:""},null,8,["modelValue"])])),_:1}),(0,t.Wm)(V,{label:"项目进度"},{default:(0,t.w5)((()=>[(0,t.Wm)(W,{modelValue:g.form.projectProgress,"onUpdate:modelValue":a[4]||(a[4]=e=>g.form.projectProgress=e),style:{width:"70%"}},null,8,["modelValue"])])),_:1}),(0,t.Wm)(V,{label:"研究类别"},{default:(0,t.w5)((()=>[(0,t.Wm)(W,{modelValue:g.form.projectType,"onUpdate:modelValue":a[5]||(a[5]=e=>g.form.projectType=e),style:{width:"70%"},disabled:""},null,8,["modelValue"])])),_:1}),(0,t.Wm)(V,{label:"研究级别"},{default:(0,t.w5)((()=>[(0,t.Wm)(W,{modelValue:g.form.projectLevel,"onUpdate:modelValue":a[6]||(a[6]=e=>g.form.projectLevel=e),style:{width:"70%"},disabled:""},null,8,["modelValue"])])),_:1}),(0,t.Wm)(V,{label:"项目积分"},{default:(0,t.w5)((()=>[(0,t.Wm)(W,{modelValue:g.form.projectMark,"onUpdate:modelValue":a[7]||(a[7]=e=>g.form.projectMark=e),style:{width:"70%"},disabled:""},null,8,["modelValue"])])),_:1})])),_:1},8,["model"])])),_:1},8,["modelValue"])])])}var g=l(4471),c={name:"Project",components:{},data(){return{form:{},dialogVisible:!1,search:"",user:{},currentPage:1,pageSize:10,total:0,tableData:[]}},created(){this.message1=this.$route.query.userid,this.message2=this.$route.query.message;let e=localStorage.getItem("user")||"{}";this.user=JSON.parse(e),g.Z.get("/user/"+this.user.userid).then((e=>{"0"===e.code&&(this.user=e.data)})),this.search=this.message1,this.load()},methods:{load(){g.Z.get("/project",{params:{pageNum:this.currentPage,pageSize:this.pageSize,search:this.search}}).then((e=>{console.log(e),this.tableData=e.data.records,this.total=e.data.total}))},add(){this.dialogVisible=!0,this.form={}},save(){this.form.projectId?g.Z.put("/project",this.form).then((e=>{console.log(e),"0"===e.code?this.$message({type:"success",message:"更新成功！"}):this.$message({type:"error",message:e.msg}),this.load(),this.dialogVisible=!1})):g.Z.post("/project",this.form).then((e=>{console.log(e),"0"===e.code?this.$message({type:"success",message:"新增成功！"}):this.$message({type:"error",message:e.msg}),this.load(),this.dialogVisible=!1}))},returnhere(){this.$router.push({path:"/oneofficegroup",query:{message:this.message2}})},handleEdit(e){this.form=JSON.parse(JSON.stringify(e)),this.dialogVisible=!0},details(e){this.$router.push({path:"/oneitemgroup",query:{message:e.projectId,asd1:e.firstMark,asd2:e.secondMark,asd3:e.threeMark}})},handleSizeChange(e){this.pageSize=e,this.load()},handleCurrentChange(e){this.currentPage=e,this.load()}}},f=l(3744);const b=(0,f.Z)(c,[["render",h]]);var w=b}}]);
//# sourceMappingURL=99.8c1018a1.js.map