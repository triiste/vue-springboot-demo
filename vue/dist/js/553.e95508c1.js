"use strict";(self["webpackChunkspringboot_vue_demo2"]=self["webpackChunkspringboot_vue_demo2"]||[]).push([[553],{5553:function(e,l,a){a.r(l),a.d(l,{default:function(){return W}});var t=a(6252);const r={style:{padding:"10px"}},o={key:0,style:{margin:"10px 0"}},d=(0,t.Uk)("新增"),i={key:1,style:{margin:"10px 0"}},s=(0,t.Uk)("查询"),m=(0,t.Uk)("个人积分情况 "),u={style:{margin:"10px 0"}},p={class:"dialog-footer"},n=(0,t.Uk)("确定"),h=(0,t.Uk)("取消");function g(e,l,a,g,c,b){const f=(0,t.up)("el-button"),w=(0,t.up)("el-input"),W=(0,t.up)("el-table-column"),V=(0,t.up)("el-table"),k=(0,t.up)("el-pagination"),y=(0,t.up)("el-form-item"),_=(0,t.up)("el-form"),C=(0,t.up)("el-dialog");return(0,t.wg)(),(0,t.iD)("div",r,[1!=this.user.role&&2!=this.user.role?((0,t.wg)(),(0,t.iD)("div",o,[(0,t.Wm)(f,{type:"primary",onClick:b.add},{default:(0,t.w5)((()=>[d])),_:1},8,["onClick"])])):(0,t.kq)("",!0),2!=this.user.role&&1!=this.user.role?((0,t.wg)(),(0,t.iD)("div",i,[(0,t.Wm)(w,{style:{width:"20%"},modelValue:c.search,"onUpdate:modelValue":l[0]||(l[0]=e=>c.search=e),size:"large",placeholder:"请输入项目ID或者组长工号",clearable:""},null,8,["modelValue"]),(0,t.Wm)(f,{type:"primary",style:{"margin-left":"5px"},onClick:b.load},{default:(0,t.w5)((()=>[s])),_:1},8,["onClick"])])):(0,t.kq)("",!0),(0,t.Wm)(V,{data:c.tableData,stripe:"",border:"",style:{width:"100%"}},{default:(0,t.w5)((()=>[(0,t.Wm)(W,{prop:"projectId",label:"ID",sortable:""}),(0,t.Wm)(W,{prop:"projectItemid",label:"项目编号",sortable:""}),(0,t.Wm)(W,{prop:"projectName",label:"项目名"}),(0,t.Wm)(W,{prop:"groupId",label:"组号"}),(0,t.Wm)(W,{prop:"projectgroupId",label:"项目组长ID"}),(0,t.Wm)(W,{prop:"projectDetial",label:"研究内容"}),(0,t.Wm)(W,{prop:"projectCycle",label:"研究周期"}),(0,t.Wm)(W,{prop:"projectProgress",label:"项目进度"}),(0,t.Wm)(W,{prop:"projectProduct",label:"成果形式"}),(0,t.Wm)(W,{prop:"projectType",label:"研究类别"}),(0,t.Wm)(W,{prop:"projectLevel",label:"研究级别"}),(0,t.Wm)(W,{prop:"projectMark",label:"项目积分"}),(0,t.Wm)(W,{prop:"firstMark",label:"第一阶段积分"}),(0,t.Wm)(W,{prop:"secondMark",label:"第二阶段积分"}),(0,t.Wm)(W,{prop:"threeMark",label:"第三阶段积分"}),(0,t.Wm)(W,{prop:"projectReward",label:"奖励积分"}),(0,t.Wm)(W,{fixed:"right",label:"操作",width:"120"},{default:(0,t.w5)((e=>[(0,t.Wm)(f,{link:"",type:"primary",size:"small",onClick:l=>b.handleEdit(e.row)},{default:(0,t.w5)((()=>[m])),_:2},1032,["onClick"])])),_:1})])),_:1},8,["data"]),(0,t._)("div",u,[(0,t.Wm)(k,{currentPage:c.currentPage,"onUpdate:currentPage":l[1]||(l[1]=e=>c.currentPage=e),"page-sizes":[5,10,20],"page-size":c.pageSize,disabled:e.disabled,layout:"total, sizes, prev, pager, next, jumper",total:c.total,onSizeChange:b.handleSizeChange,onCurrentChange:b.handleCurrentChange},null,8,["currentPage","page-size","disabled","total","onSizeChange","onCurrentChange"]),(0,t.Wm)(C,{modelValue:c.dialogVisible,"onUpdate:modelValue":l[13]||(l[13]=e=>c.dialogVisible=e),title:"个人积分情况",width:"30%"},{footer:(0,t.w5)((()=>[(0,t._)("span",p,[(0,t.Wm)(f,{type:"primary",onClick:b.save},{default:(0,t.w5)((()=>[n])),_:1},8,["onClick"]),(0,t.Wm)(f,{onClick:l[12]||(l[12]=e=>c.dialogVisible=!1)},{default:(0,t.w5)((()=>[h])),_:1})])])),default:(0,t.w5)((()=>[(0,t.Wm)(_,{model:c.form,"label-width":"120px",disabled:""},{default:(0,t.w5)((()=>[(0,t.Wm)(y,{label:"ID"},{default:(0,t.w5)((()=>[(0,t.Wm)(w,{modelValue:c.form.id,"onUpdate:modelValue":l[2]||(l[2]=e=>c.form.id=e),style:{width:"70%"},disabled:""},null,8,["modelValue"])])),_:1}),(0,t.Wm)(y,{label:"项目ID"},{default:(0,t.w5)((()=>[(0,t.Wm)(w,{modelValue:c.form.projectid,"onUpdate:modelValue":l[3]||(l[3]=e=>c.form.projectid=e),style:{width:"70%"},disabled:""},null,8,["modelValue"])])),_:1}),(0,t.Wm)(y,{label:"用户ID"},{default:(0,t.w5)((()=>[(0,t.Wm)(w,{modelValue:c.form.userid,"onUpdate:modelValue":l[4]||(l[4]=e=>c.form.userid=e),style:{width:"70%"},disabled:""},null,8,["modelValue"])])),_:1}),(0,t.Wm)(y,{label:"用户积分"},{default:(0,t.w5)((()=>[(0,t.Wm)(w,{modelValue:c.form.mark,"onUpdate:modelValue":l[5]||(l[5]=e=>c.form.mark=e),style:{width:"70%"},disabled:""},null,8,["modelValue"])])),_:1}),(0,t.Wm)(y,{label:"操作员"},{default:(0,t.w5)((()=>[(0,t.Wm)(w,{modelValue:c.form.handler,"onUpdate:modelValue":l[6]||(l[6]=e=>c.form.handler=e),style:{width:"70%"},disabled:""},null,8,["modelValue"])])),_:1}),(0,t.Wm)(y,{label:"项目进度"},{default:(0,t.w5)((()=>[(0,t.Wm)(w,{modelValue:c.form.projectProgress,"onUpdate:modelValue":l[7]||(l[7]=e=>c.form.projectProgress=e),style:{width:"70%"},disabled:""},null,8,["modelValue"])])),_:1}),(0,t.Wm)(y,{label:"第一阶段积分"},{default:(0,t.w5)((()=>[(0,t.Wm)(w,{modelValue:c.form.firstMark,"onUpdate:modelValue":l[8]||(l[8]=e=>c.form.firstMark=e),style:{width:"70%"}},null,8,["modelValue"])])),_:1}),(0,t.Wm)(y,{label:"第二阶段积分"},{default:(0,t.w5)((()=>[(0,t.Wm)(w,{modelValue:c.form.secondMark,"onUpdate:modelValue":l[9]||(l[9]=e=>c.form.secondMark=e),style:{width:"70%"}},null,8,["modelValue"])])),_:1}),(0,t.Wm)(y,{label:"第三阶段积分"},{default:(0,t.w5)((()=>[(0,t.Wm)(w,{modelValue:c.form.threeMark,"onUpdate:modelValue":l[10]||(l[10]=e=>c.form.threeMark=e),style:{width:"70%"}},null,8,["modelValue"])])),_:1}),(0,t.Wm)(y,{label:"附加分"},{default:(0,t.w5)((()=>[(0,t.Wm)(w,{modelValue:c.form.groupReward,"onUpdate:modelValue":l[11]||(l[11]=e=>c.form.groupReward=e),style:{width:"70%"}},null,8,["modelValue"])])),_:1})])),_:1},8,["model"])])),_:1},8,["modelValue"])])])}var c=a(4471),b={name:"Project",components:{},data(){return{form:{},dialogVisible:!1,search:"",user:{},currentPage:1,pageSize:10,total:0,tableData:[],totaltableData:[],i:0,j:0,record:[],k:0}},created(){this.message1=this.$route.query.jmessage;let e=localStorage.getItem("user")||"{}";this.user=JSON.parse(e),c.Z.get("/user/"+this.user.userid).then((e=>{"0"===e.code&&(this.user=e.data)})),console.log(this.user.userid),this.load()},methods:{load(){c.Z.get("/project/test",{params:{userid:this.user.userid,pageNum:this.currentPage,pageSize:this.pageSize}}).then((e=>{this.tableData=e.data,console.log(e),this.total=e.total}))},add(){this.dialogVisible=!0,this.form={}},save(){this.dialogVisible=!1},sleep(e){var l=(new Date).getTime();while((new Date).getTime()<l+e);},handleEdit(e){this.search=this.user.userid,c.Z.get("/group/finddata",{params:{userid:this.user.userid,projectId:e.projectId}}).then((e=>{"0"===e.code?(this.form=e.data,this.dialogVisible=!0):this.$message({type:"error",message:e.msg})}))},handleSizeChange(e){this.pageSize=e,this.load()},handleCurrentChange(e){this.currentPage=e,this.load()}}},f=a(3744);const w=(0,f.Z)(b,[["render",g]]);var W=w}}]);
//# sourceMappingURL=553.e95508c1.js.map