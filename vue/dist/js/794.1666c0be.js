"use strict";(self["webpackChunkspringboot_vue_demo2"]=self["webpackChunkspringboot_vue_demo2"]||[]).push([[794],{5794:function(e,l,a){a.r(l),a.d(l,{default:function(){return S}});var o=a(6252);const d={style:{padding:"10px"}},t={style:{margin:"10px 0"}},s=(0,o.Uk)("新增"),r=(0,o.Uk)("个人信息"),m=(0,o.Uk)("编辑"),i=(0,o.Uk)("删除"),u={style:{margin:"10px 0"}},n={class:"dialog-footer"},p=(0,o.Uk)("确定"),f=(0,o.Uk)("取消"),h=(0,o.Uk)("% "),g=(0,o.Uk)("% "),w=(0,o.Uk)("% "),c={class:"dialog-footer"},V=(0,o.Uk)("确定"),b=(0,o.Uk)("取消"),W={class:"dialog-footer"},y=(0,o.Uk)("确定"),_=(0,o.Uk)("取消");function k(e,l,a,k,U,C){const j=(0,o.up)("el-button"),z=(0,o.up)("el-table-column"),S=(0,o.up)("el-popconfirm"),D=(0,o.up)("el-table"),v=(0,o.up)("el-pagination"),P=(0,o.up)("el-input"),x=(0,o.up)("el-form-item"),$=(0,o.up)("el-form"),I=(0,o.up)("el-dialog");return(0,o.wg)(),(0,o.iD)("div",d,[(0,o._)("div",t,[3!==this.user.role?((0,o.wg)(),(0,o.j4)(j,{key:0,type:"primary",onClick:C.add},{default:(0,o.w5)((()=>[s])),_:1},8,["onClick"])):(0,o.kq)("",!0)]),(0,o.Wm)(D,{data:U.tableData,stripe:"",border:"",style:{width:"100%"}},{default:(0,o.w5)((()=>[(0,o.Wm)(z,{prop:"id",label:"ID",sortable:""}),(0,o.Wm)(z,{prop:"projectid",label:"项目ID",sortable:""}),(0,o.Wm)(z,{prop:"userid",label:"用户ID"}),(0,o.Wm)(z,{prop:"projectProgress",label:"项目进程"}),(0,o.Wm)(z,{prop:"firstMark",label:"第一阶段积分"}),(0,o.Wm)(z,{prop:"secondMark",label:"第二阶段积分"}),(0,o.Wm)(z,{prop:"threeMark",label:"第三阶段积分"}),(0,o.Wm)(z,{prop:"groupReward",label:"附加分"}),(0,o.Wm)(z,{fixed:"right",label:"操作",width:"120"},{default:(0,o.w5)((e=>[(0,o.Wm)(j,{type:"primary",size:"small",onClick:l=>C.perMessage(e.row)},{default:(0,o.w5)((()=>[r])),_:2},1032,["onClick"]),3!==this.user.role?((0,o.wg)(),(0,o.j4)(j,{key:0,link:"",type:"primary",size:"small",onClick:l=>C.handleEdit(e.row)},{default:(0,o.w5)((()=>[m])),_:2},1032,["onClick"])):(0,o.kq)("",!0),(0,o.Wm)(S,{title:"确认删除吗？",onConfirm:l=>C.handleDelete(e.row.id)},{reference:(0,o.w5)((()=>[3!==this.user.role?((0,o.wg)(),(0,o.j4)(j,{key:0,link:"",type:"danger",size:"small"},{default:(0,o.w5)((()=>[i])),_:1})):(0,o.kq)("",!0)])),_:2},1032,["onConfirm"])])),_:1})])),_:1},8,["data"]),(0,o._)("div",u,[(0,o.Wm)(v,{currentPage:U.currentPage,"onUpdate:currentPage":l[0]||(l[0]=e=>U.currentPage=e),"page-sizes":[5,10,20],"page-size":U.pageSize,disabled:e.disabled,layout:"total, sizes, prev, pager, next, jumper",total:U.total,onSizeChange:C.handleSizeChange,onCurrentChange:C.handleCurrentChange},null,8,["currentPage","page-size","disabled","total","onSizeChange","onCurrentChange"]),(0,o.Wm)(I,{modelValue:U.dialogVisible,"onUpdate:modelValue":l[5]||(l[5]=e=>U.dialogVisible=e),title:"提示",width:"30%"},{footer:(0,o.w5)((()=>[(0,o._)("span",n,[(0,o.Wm)(j,{type:"primary",onClick:C.save},{default:(0,o.w5)((()=>[p])),_:1},8,["onClick"]),(0,o.Wm)(j,{onClick:l[4]||(l[4]=e=>U.dialogVisible=!1)},{default:(0,o.w5)((()=>[f])),_:1})])])),default:(0,o.w5)((()=>[(0,o.Wm)($,{model:U.form,"label-width":"120px"},{default:(0,o.w5)((()=>[(0,o.Wm)(x,{label:"项目ID"},{default:(0,o.w5)((()=>[(0,o.Wm)(P,{modelValue:U.form.projectid,"onUpdate:modelValue":l[1]||(l[1]=e=>U.form.projectid=e),style:{width:"70%"},disabled:""},null,8,["modelValue"])])),_:1}),(0,o.Wm)(x,{label:"用户ID"},{default:(0,o.w5)((()=>[(0,o.Wm)(P,{modelValue:U.form.userid,"onUpdate:modelValue":l[2]||(l[2]=e=>U.form.userid=e),style:{width:"70%"}},null,8,["modelValue"])])),_:1}),(0,o.Wm)(x,{label:"项目进度"},{default:(0,o.w5)((()=>[(0,o.Wm)(P,{modelValue:U.form.projectProgress,"onUpdate:modelValue":l[3]||(l[3]=e=>U.form.projectProgress=e),style:{width:"70%"}},null,8,["modelValue"])])),_:1})])),_:1},8,["model"])])),_:1},8,["modelValue"]),(0,o.Wm)(I,{modelValue:U.dialog,"onUpdate:modelValue":l[14]||(l[14]=e=>U.dialog=e),title:"提示",width:"30%"},{footer:(0,o.w5)((()=>[(0,o._)("span",c,[(0,o.Wm)(j,{type:"primary",onClick:C.save},{default:(0,o.w5)((()=>[V])),_:1},8,["onClick"]),(0,o.Wm)(j,{onClick:l[13]||(l[13]=e=>U.dialog=!1)},{default:(0,o.w5)((()=>[b])),_:1})])])),default:(0,o.w5)((()=>[(0,o.Wm)($,{model:U.form,"label-width":"120px"},{default:(0,o.w5)((()=>[(0,o.Wm)(x,{label:"项目ID"},{default:(0,o.w5)((()=>[(0,o.Wm)(P,{modelValue:U.form.projectid,"onUpdate:modelValue":l[6]||(l[6]=e=>U.form.projectid=e),style:{width:"70%"},disabled:""},null,8,["modelValue"])])),_:1}),(0,o.Wm)(x,{label:"用户ID"},{default:(0,o.w5)((()=>[(0,o.Wm)(P,{modelValue:U.form.userid,"onUpdate:modelValue":l[7]||(l[7]=e=>U.form.userid=e),style:{width:"70%"},disabled:""},null,8,["modelValue"])])),_:1}),(0,o.Wm)(x,{label:"项目进度"},{default:(0,o.w5)((()=>[(0,o.Wm)(P,{modelValue:U.form.projectProgress,"onUpdate:modelValue":l[8]||(l[8]=e=>U.form.projectProgress=e),style:{width:"70%"}},null,8,["modelValue"])])),_:1}),(0,o.Wm)(x,{label:"第一阶段积分比"},{default:(0,o.w5)((()=>[(0,o.Wm)(P,{modelValue:U.form.first,"onUpdate:modelValue":l[9]||(l[9]=e=>U.form.first=e),style:{width:"70%"}},null,8,["modelValue"]),h])),_:1}),(0,o.Wm)(x,{label:"第二阶段积分比"},{default:(0,o.w5)((()=>[(0,o.Wm)(P,{modelValue:U.form.second,"onUpdate:modelValue":l[10]||(l[10]=e=>U.form.second=e),style:{width:"70%"}},null,8,["modelValue"]),g])),_:1}),(0,o.Wm)(x,{label:"第三阶段积分比"},{default:(0,o.w5)((()=>[(0,o.Wm)(P,{modelValue:U.form.three,"onUpdate:modelValue":l[11]||(l[11]=e=>U.form.three=e),style:{width:"70%"}},null,8,["modelValue"]),w])),_:1}),(0,o.Wm)(x,{label:"附加分"},{default:(0,o.w5)((()=>[(0,o.Wm)(P,{modelValue:U.form.groupReward,"onUpdate:modelValue":l[12]||(l[12]=e=>U.form.groupReward=e),style:{width:"70%"}},null,8,["modelValue"])])),_:1})])),_:1},8,["model"])])),_:1},8,["modelValue"]),(0,o.Wm)(I,{modelValue:U.perdialog,"onUpdate:modelValue":l[27]||(l[27]=e=>U.perdialog=e),title:"提示",width:"30%"},{footer:(0,o.w5)((()=>[(0,o._)("span",W,[(0,o.Wm)(j,{type:"primary",onClick:C.save},{default:(0,o.w5)((()=>[y])),_:1},8,["onClick"]),(0,o.Wm)(j,{onClick:l[26]||(l[26]=e=>U.perdialog=!1)},{default:(0,o.w5)((()=>[_])),_:1})])])),default:(0,o.w5)((()=>[(0,o.Wm)($,{model:U.form,"label-width":"120px"},{default:(0,o.w5)((()=>[(0,o.Wm)(x,{label:"ID"},{default:(0,o.w5)((()=>[(0,o.Wm)(P,{modelValue:U.form.userid,"onUpdate:modelValue":l[15]||(l[15]=e=>U.form.userid=e),style:{width:"70%"},disabled:""},null,8,["modelValue"])])),_:1}),(0,o.Wm)(x,{label:"工号"},{default:(0,o.w5)((()=>[(0,o.Wm)(P,{modelValue:U.form.userjobid,"onUpdate:modelValue":l[16]||(l[16]=e=>U.form.userjobid=e),style:{width:"70%"},disabled:""},null,8,["modelValue"])])),_:1}),(0,o.Wm)(x,{label:"用户名"},{default:(0,o.w5)((()=>[(0,o.Wm)(P,{modelValue:U.form.username,"onUpdate:modelValue":l[17]||(l[17]=e=>U.form.username=e),style:{width:"70%"},disabled:""},null,8,["modelValue"])])),_:1}),(0,o.Wm)(x,{label:"电话"},{default:(0,o.w5)((()=>[(0,o.Wm)(P,{modelValue:U.form.telephone,"onUpdate:modelValue":l[18]||(l[18]=e=>U.form.telephone=e),style:{width:"70%"},disabled:""},null,8,["modelValue"])])),_:1}),(0,o.Wm)(x,{label:"年龄"},{default:(0,o.w5)((()=>[(0,o.Wm)(P,{modelValue:U.form.age,"onUpdate:modelValue":l[19]||(l[19]=e=>U.form.age=e),style:{width:"70%"},disabled:""},null,8,["modelValue"])])),_:1}),(0,o.Wm)(x,{label:"性别"},{default:(0,o.w5)((()=>[(0,o.Wm)(P,{modelValue:U.form.sex,"onUpdate:modelValue":l[20]||(l[20]=e=>U.form.sex=e),style:{width:"70%"},disabled:""},null,8,["modelValue"])])),_:1}),(0,o.Wm)(x,{label:"地址"},{default:(0,o.w5)((()=>[(0,o.Wm)(P,{modelValue:U.form.address,"onUpdate:modelValue":l[21]||(l[21]=e=>U.form.address=e),style:{width:"70%"},disabled:""},null,8,["modelValue"])])),_:1}),(0,o.Wm)(x,{label:"职位"},{default:(0,o.w5)((()=>[(0,o.Wm)(P,{modelValue:U.form.position,"onUpdate:modelValue":l[22]||(l[22]=e=>U.form.position=e),style:{width:"70%"},disabled:""},null,8,["modelValue"])])),_:1}),(0,o.Wm)(x,{label:"部门"},{default:(0,o.w5)((()=>[(0,o.Wm)(P,{modelValue:U.form.permission,"onUpdate:modelValue":l[23]||(l[23]=e=>U.form.permission=e),style:{width:"70%"},disabled:""},null,8,["modelValue"])])),_:1}),(0,o.Wm)(x,{label:"所属室"},{default:(0,o.w5)((()=>[(0,o.Wm)(P,{modelValue:U.form.officeid,"onUpdate:modelValue":l[24]||(l[24]=e=>U.form.officeid=e),style:{width:"70%"},disabled:""},null,8,["modelValue"])])),_:1}),(0,o.Wm)(x,{label:"个人积分"},{default:(0,o.w5)((()=>[(0,o.Wm)(P,{modelValue:U.form.mark,"onUpdate:modelValue":l[25]||(l[25]=e=>U.form.mark=e),style:{width:"70%"},disabled:""},null,8,["modelValue"])])),_:1})])),_:1},8,["model"])])),_:1},8,["modelValue"])])])}var U=a(4471),C={name:"Group",components:{},data(){return{form:{},dialogVisible:!1,dialog:!1,perdialog:!1,search:"",user:{},projectid1:"",currentPage:1,pageSize:10,total:0,tableData:[],message1:"",asd1:"",asd2:"",asd3:""}},created(){this.message1=this.$route.query.message,this.asd1=this.$route.query.asd1,this.asd2=this.$route.query.asd2,this.asd3=this.$route.query.asd3;let e=localStorage.getItem("user")||"{}";this.user=JSON.parse(e),U.Z.get("/user/"+this.user.userid).then((e=>{"0"===e.code&&(this.user=e.data)})),this.search=this.message1,this.load()},methods:{load(){U.Z.get("/group",{params:{pageNum:this.currentPage,pageSize:this.pageSize,search:this.search}}).then((e=>{console.log(e),this.tableData=e.data.records,this.total=e.data.total}))},perMessage(e){console.log(e.userid),U.Z.get("/user/"+e.userid).then((e=>{"0"===e.code&&(this.form=e.data)})),this.form=JSON.parse(JSON.stringify(e)),this.perdialog=!0},add(){this.dialogVisible=!0,this.form={},this.form.projectid=this.search},save(){this.form.id?(this.form.firstMark=this.form.first*this.asd1*.01,this.form.secondMark=this.form.second*this.asd2*.01,this.form.threeMark=this.form.three*this.asd3*.01,U.Z.put("/group",this.form).then((e=>{console.log(e),"0"===e.code?this.$message({type:"success",message:"更新成功！"}):this.$message({type:"error",message:e.msg}),this.dialog=!1,this.load()}))):U.Z.post("/group",this.form).then((e=>{"0"===e.code?this.$message({type:"success",message:"新增成功！"}):this.$message({type:"error",message:e.msg}),this.load(),this.dialogVisible=!1}))},handleEdit(e){this.form=JSON.parse(JSON.stringify(e)),this.dialog=!0},handleDelete(e){console.log(e),U.Z["delete"]("/group/"+e).then((e=>{"0"===e.code?this.$message({type:"success",message:"删除成功！"}):this.$message({type:"error",message:e.msg}),this.load()}))},handleSizeChange(e){this.pageSize=e,this.load()},handleCurrentChange(e){this.currentPage=e,this.load()}}},j=a(3744);const z=(0,j.Z)(C,[["render",k]]);var S=z}}]);
//# sourceMappingURL=794.1666c0be.js.map