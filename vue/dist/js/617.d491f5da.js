"use strict";(self["webpackChunkspringboot_vue_demo2"]=self["webpackChunkspringboot_vue_demo2"]||[]).push([[617],{2617:function(e,l,a){a.r(l),a.d(l,{default:function(){return y}});var t=a(6252);const o={style:{padding:"10px"}},r={style:{margin:"10px 0"}},d=(0,t.Uk)("新增"),s={style:{margin:"10px 0"}},i=(0,t.Uk)("查询"),m=(0,t.Uk)("编辑"),p=(0,t.Uk)("删除"),n={style:{margin:"10px 0"}},u={class:"dialog-footer"},c=(0,t.Uk)("确定"),g=(0,t.Uk)("取消");function h(e,l,a,h,f,b){const w=(0,t.up)("el-button"),W=(0,t.up)("el-input"),y=(0,t.up)("el-table-column"),V=(0,t.up)("el-popconfirm"),j=(0,t.up)("el-table"),C=(0,t.up)("el-pagination"),_=(0,t.up)("el-form-item"),k=(0,t.up)("el-form"),z=(0,t.up)("el-dialog");return(0,t.wg)(),(0,t.iD)("div",o,[(0,t._)("div",r,[(0,t.Wm)(w,{type:"primary",onClick:b.add},{default:(0,t.w5)((()=>[d])),_:1},8,["onClick"])]),(0,t._)("div",s,[(0,t.Wm)(W,{style:{width:"20%"},modelValue:f.search,"onUpdate:modelValue":l[0]||(l[0]=e=>f.search=e),size:"large",placeholder:"请输入关键字",clearable:""},null,8,["modelValue"]),(0,t.Wm)(w,{type:"primary",style:{"margin-left":"5px"},onClick:b.load},{default:(0,t.w5)((()=>[i])),_:1},8,["onClick"])]),(0,t.Wm)(j,{data:f.tableData,stripe:"",border:"",style:{width:"100%"}},{default:(0,t.w5)((()=>[(0,t.Wm)(y,{prop:"projectId",label:"ID",sortable:""}),(0,t.Wm)(y,{prop:"projectItemid",label:"项目编号",sortable:""}),(0,t.Wm)(y,{prop:"projectName",label:"项目名"}),(0,t.Wm)(y,{prop:"groupId",label:"组号"}),(0,t.Wm)(y,{prop:"projectDetial",label:"研究内容"}),(0,t.Wm)(y,{prop:"projectCycle",label:"研究周期"}),(0,t.Wm)(y,{prop:"projectProgress",label:"项目进度"}),(0,t.Wm)(y,{prop:"projectProduct",label:"成果形式"}),(0,t.Wm)(y,{prop:"projectType",label:"研究类别"}),(0,t.Wm)(y,{prop:"projectLevel",label:"研究级别"}),(0,t.Wm)(y,{prop:"projectMark",label:"项目积分"}),(0,t.Wm)(y,{prop:"projectReward",label:"奖励积分"}),(0,t.Wm)(y,{fixed:"right",label:"操作",width:"120"},{default:(0,t.w5)((e=>[(0,t.Wm)(w,{link:"",type:"primary",size:"small",onClick:l=>b.handleEdit(e.row)},{default:(0,t.w5)((()=>[m])),_:2},1032,["onClick"]),(0,t.Wm)(V,{title:"确认删除吗？",onConfirm:l=>b.handleDelete(e.row.projectId)},{reference:(0,t.w5)((()=>[(0,t.Wm)(w,{link:"",type:"danger",size:"small"},{default:(0,t.w5)((()=>[p])),_:1})])),_:2},1032,["onConfirm"])])),_:1})])),_:1},8,["data"]),(0,t._)("div",n,[(0,t.Wm)(C,{currentPage:f.currentPage,"onUpdate:currentPage":l[1]||(l[1]=e=>f.currentPage=e),"page-sizes":[5,10,20],"page-size":f.pageSize,disabled:e.disabled,layout:"total, sizes, prev, pager, next, jumper",total:f.total,onSizeChange:b.handleSizeChange,onCurrentChange:b.handleCurrentChange},null,8,["currentPage","page-size","disabled","total","onSizeChange","onCurrentChange"]),(0,t.Wm)(z,{modelValue:f.dialogVisible,"onUpdate:modelValue":l[9]||(l[9]=e=>f.dialogVisible=e),title:"提示",width:"30%"},{footer:(0,t.w5)((()=>[(0,t._)("span",u,[(0,t.Wm)(w,{type:"primary",onClick:b.save},{default:(0,t.w5)((()=>[c])),_:1},8,["onClick"]),(0,t.Wm)(w,{onClick:l[8]||(l[8]=e=>f.dialogVisible=!1)},{default:(0,t.w5)((()=>[g])),_:1})])])),default:(0,t.w5)((()=>[(0,t.Wm)(k,{model:f.form,"label-width":"120px"},{default:(0,t.w5)((()=>[(0,t.Wm)(_,{label:"项目编号"},{default:(0,t.w5)((()=>[(0,t.Wm)(W,{modelValue:f.form.projectItemid,"onUpdate:modelValue":l[2]||(l[2]=e=>f.form.projectItemid=e),style:{width:"70%"}},null,8,["modelValue"])])),_:1}),(0,t.Wm)(_,{label:"项目名称"},{default:(0,t.w5)((()=>[(0,t.Wm)(W,{modelValue:f.form.projectName,"onUpdate:modelValue":l[3]||(l[3]=e=>f.form.projectName=e),style:{width:"70%"}},null,8,["modelValue"])])),_:1}),(0,t.Wm)(_,{label:"项目组号"},{default:(0,t.w5)((()=>[(0,t.Wm)(W,{modelValue:f.form.groupId,"onUpdate:modelValue":l[4]||(l[4]=e=>f.form.groupId=e),style:{width:"70%"}},null,8,["modelValue"])])),_:1}),(0,t.Wm)(_,{label:"项目进程"},{default:(0,t.w5)((()=>[(0,t.Wm)(W,{modelValue:f.form.projectProgress,"onUpdate:modelValue":l[5]||(l[5]=e=>f.form.projectProgress=e),style:{width:"70%"}},null,8,["modelValue"])])),_:1}),(0,t.Wm)(_,{label:"项目积分"},{default:(0,t.w5)((()=>[(0,t.Wm)(W,{modelValue:f.form.projectMark,"onUpdate:modelValue":l[6]||(l[6]=e=>f.form.projectMark=e),style:{width:"70%"}},null,8,["modelValue"])])),_:1}),(0,t.Wm)(_,{label:"奖励积分"},{default:(0,t.w5)((()=>[(0,t.Wm)(W,{modelValue:f.form.projectReward,"onUpdate:modelValue":l[7]||(l[7]=e=>f.form.projectReward=e),style:{width:"70%"}},null,8,["modelValue"])])),_:1})])),_:1},8,["model"])])),_:1},8,["modelValue"])])])}var f=a(4471),b={name:"Project",components:{},data(){return{form:{},dialogVisible:!1,search:"",currentPage:1,pageSize:10,total:0,tableData:[]}},created(){this.load()},methods:{load(){f.Z.get("/project",{params:{pageNum:this.currentPage,pageSize:this.pageSize,search:this.search}}).then((e=>{console.log(e),this.tableData=e.data.records,this.total=e.data.total}))},add(){this.dialogVisible=!0,this.form={}},save(){this.form.projectId?f.Z.put("/project",this.form).then((e=>{console.log(e),"0"==e.code?this.$message({type:"success",message:"更新成功！"}):this.$message({type:"error",message:e.msg}),this.load(),this.dialogVisible=!1})):f.Z.post("/project",this.form).then((e=>{console.log(e),"0"==e.code?this.$message({type:"success",message:"新增成功！"}):this.$message({type:"error",message:e.msg}),this.load(),this.dialogVisible=!1}))},handleEdit(e){this.form=JSON.parse(JSON.stringify(e)),this.dialogVisible=!0},handleDelete(e){console.log(e),f.Z["delete"]("/project/"+e).then((e=>{"0"==e.code?this.$message({type:"success",message:"删除成功！"}):this.$message({type:"error",message:e.msg}),this.load()}))},handleSizeChange(e){this.pageSize=e,this.load()},handleCurrentChange(e){this.currentPage=e,this.load()}}},w=a(3744);const W=(0,w.Z)(b,[["render",h]]);var y=W}}]);
//# sourceMappingURL=617.d491f5da.js.map