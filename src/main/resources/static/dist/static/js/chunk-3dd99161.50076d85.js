(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-3dd99161"],{"4f76":function(e,t,r){"use strict";var i=r("e347"),o=r.n(i);o.a},"663e":function(e,t,r){"use strict";r.d(t,"g",(function(){return o})),r.d(t,"d",(function(){return s})),r.d(t,"f",(function(){return a})),r.d(t,"e",(function(){return n})),r.d(t,"a",(function(){return l})),r.d(t,"b",(function(){return d})),r.d(t,"c",(function(){return c}));var i=r("b775");function o(e){var t=!(arguments.length>1&&void 0!==arguments[1])||arguments[1];return Object(i["a"])({url:"/electronic/workOrder/startWorkOrder",method:"post",loading:t,data:e})}function s(e){var t=!(arguments.length>1&&void 0!==arguments[1])||arguments[1];return Object(i["a"])({url:"/electronic/workOrder/queryWorkOrder",method:"post",loading:t,data:e})}function a(e){var t=!(arguments.length>1&&void 0!==arguments[1])||arguments[1];return Object(i["a"])({url:"/electronic/workOrder/queryWorkOrderToMe",method:"post",loading:t,data:e})}function n(e){var t=!(arguments.length>1&&void 0!==arguments[1])||arguments[1];return Object(i["a"])({url:"/electronic/workOrder/queryWorkOrderDetail",method:"post",loading:t,data:e})}function l(e){var t=!(arguments.length>1&&void 0!==arguments[1])||arguments[1];return Object(i["a"])({url:"/electronic/workOrder/approveWorkOrder",method:"post",loading:t,data:e})}function d(e){var t=!(arguments.length>1&&void 0!==arguments[1])||arguments[1];return Object(i["a"])({url:"/electronic/workOrder/approverCarbonCopy",method:"post",loading:t,data:e})}function c(e){var t=!(arguments.length>1&&void 0!==arguments[1])||arguments[1];return Object(i["a"])({url:"/electronic/workOrder/queryApproverCarbonCopy",method:"post",loading:t,data:e})}},ba83:function(e,t,r){"use strict";r.r(t);var i=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"contianer",attrs:{id:"printMe"}},[r("div",[r("div",{staticStyle:{float:"right"}},[e.type?r("el-button",{attrs:{size:"small",type:"primary"},on:{click:function(t){return e.handle("90")}}},[e._v("审批通过")]):e._e(),e._v(" "),e.type?r("el-button",{attrs:{size:"small",type:"danger"},on:{click:function(t){return e.handle("70")}}},[e._v("审批拒绝")]):e._e(),e._v(" "),r("el-button",{directives:[{name:"print",rawName:"v-print",value:"#printMe",expression:"'#printMe'"}],staticStyle:{"background-color":"#7f8c8d",color:"#fff"},attrs:{size:"small"}},[e._v("打印")]),e._v(" "),r("el-button",{attrs:{size:"small",type:"primary"},on:{click:function(t){return e.goback()}}},[e._v("返回")])],1),e._v(" "),e._m(0),e._v(" "),r("el-row",[r("el-col",{attrs:{span:8}},[r("div",{staticClass:"grid-content bg-purple-light"},[r("p",{staticClass:"detail-lable"},[e._v("工单编号："),r("span",{staticClass:"detail-content"},[e._v(e._s(e.workOrderDetail.workOrderCode))])])])]),e._v(" "),r("el-col",{attrs:{span:8}},[r("div",{staticClass:"grid-content bg-purple-light"},[r("p",{staticClass:"detail-lable"},[e._v("工单标题："),r("span",{staticClass:"detail-content"},[e._v(e._s(e.workOrderDetail.workOrderName))])])])])],1),e._v(" "),r("el-row",[r("el-col",{attrs:{span:8}},[r("div",{staticClass:"grid-content bg-purple"},[r("p",{staticClass:"detail-lable"},[e._v("创建人："),r("span",{staticClass:"detail-content"},[e._v(e._s(e.workOrderDetail.userName))])])])]),e._v(" "),r("el-col",{attrs:{span:8}},[r("div",{staticClass:"grid-content bg-purple"},[r("p",{staticClass:"detail-lable"},[e._v("创建时间："),r("span",{staticClass:"detail-content"},[e._v(e._s(e.workOrderDetail.operateTime))])])])]),e._v(" "),r("el-col",{attrs:{span:8}},[r("div",{staticClass:"grid-content bg-purple-light"},[r("p",{staticClass:"detail-lable"},[e._v("状态："),r("span",{staticClass:"detail-content"},[e._v(e._s(e.workOrderDetail.workOrderStatusDesc))])])])])],1),e._v(" "),r("el-row",[r("el-col",{attrs:{span:24}},[r("div",{staticClass:"grid-content bg-purple-light"},[r("p",{staticClass:"detail-lable"},[e._v("描述："+e._s(e.workOrderDetail.workOrderDesc))])])])],1)],1),e._v(" "),r("el-divider"),e._v(" "),r("div",{staticClass:"detail-title"},[r("p",[e._v("文件列表：")]),e._v(" "),r("el-row",e._l(e.workOrderDetail.workInfo,(function(t,i){return r("div",{staticClass:"fileList"},[r("el-link",{key:i,attrs:{underline:!1}},[r("span",["xls,xlsx,csv".indexOf(t.docType)>-1?r("svg-icon",{attrs:{"icon-class":"excel"}}):"ppt,pptx,pps,ppsx,pot".indexOf(t.docType)>-1?r("svg-icon",{attrs:{"icon-class":"PPT"}}):"pdf,dpt,odf".indexOf(t.docType)>-1?r("svg-icon",{attrs:{"icon-class":"PDF"}}):"doc,docx".indexOf(t.docType)>-1?r("svg-icon",{attrs:{"icon-class":"WORD"}}):"bmp,jpg,png,tif,gif,pcx,tga,exif,fpx,svg,psd,cdr,pcd,dxf,ufo,eps,ai,raw,WMF,webp,jpeg".indexOf(t.docType)>-1?r("svg-icon",{attrs:{"icon-class":"image"}}):r("svg-icon",{attrs:{"icon-class":"wendang"}}),e._v("\n          "+e._s(t.docName)+"\n        ")],1)]),e._v(" "),r("span",[r("a",{attrs:{href:t.docUrl,target:"_blank"}},[r("el-button",{attrs:{size:"mini",type:"primary"}},[e._v("下载")])],1),e._v(" "),r("el-button",{attrs:{size:"mini",type:"success"},on:{click:function(r){return e.handlePreview(t.docUrl)}}},[e._v("预览")])],1)],1)})),0)],1),e._v(" "),r("el-divider"),e._v(" "),r("div",[e._m(1),e._v(" "),r("el-timeline",e._l(e.activities,(function(t,i){return r("el-timeline-item",{key:i,attrs:{color:e.workOrderDetail.workNode.nodeId>=t.nodeId?"90"==t.nodeOperateResult?"#67C23A":70==t.nodeOperateResult?"#F56C6C":"#3498db":"",timestamp:t.nodeOperateTime&&new Date(t.nodeOperateTime).format("yyyy/MM/dd hh:mm:ss"),placement:"top",size:"large"}},[r("p",[e._v("审批人："+e._s(t.userName))]),e._v(" "),r("p",{staticClass:"approve-suggest"},[e._v("审批批注："+e._s(t.nodeOperateDesc))]),e._v(" "),r("p",{staticClass:"approve-suggest"},[e._v("抄送人员：\n            "),e._l(t.workCarbonVOList,(function(t,i){return r("el-link",{key:i,attrs:{underline:!1,type:"primary"}},[e._v("\n              "+e._s(t.userName)+"、\n            ")])}))],2),e._v(" "),r("p",[e._v("审批结果：\n            "),10==t.nodeOperateResult?r("el-link",{attrs:{underline:!1,type:"primary"}},[e._v("审批中")]):e._e(),e._v(" "),90==t.nodeOperateResult?r("el-link",{attrs:{underline:!1,type:"success"}},[e._v("审批通过")]):e._e(),e._v(" "),70==t.nodeOperateResult?r("el-link",{attrs:{underline:!1,type:"danger"}},[e._v("审批驳回")]):e._e()],1)])})),1)],1),e._v(" "),r("el-dialog",{attrs:{visible:e.dialogVisible,title:"审批处理"},on:{"update:visible":function(t){e.dialogVisible=t}}},[r("el-form",{ref:"approve",staticClass:"demo-form-inline",attrs:{model:e.approve,rules:e.rules}},[r("el-form-item",{attrs:{label:"审批批注",prop:"remark"}},[r("el-input",{attrs:{rows:3,type:"textarea"},model:{value:e.approve.remark,callback:function(t){e.$set(e.approve,"remark",t)},expression:"approve.remark"}})],1)],1),e._v(" "),r("div",{staticStyle:{"text-align":"right"}},[r("el-button",{attrs:{size:"small",type:"primary"},on:{click:function(t){return e.onSubmit(e.operateStatus,"approve")}}},[e._v("提交")])],1)],1),e._v(" "),r("el-dialog",{attrs:{visible:e.dialogVisibleSelectDept,title:"选择处理结果"},on:{"update:visible":function(t){e.dialogVisibleSelectDept=t}}},[r("el-form",{attrs:{"label-width":"80px"}},[r("el-form-item",{attrs:{label:"处理结果"}},[r("el-radio-group",{model:{value:e.approveResult,callback:function(t){e.approveResult=t},expression:"approveResult"}},[r("el-radio",{attrs:{label:1}},[e._v("审批结束")]),e._v(" "),r("el-radio",{attrs:{label:2}},[e._v("审批推送")])],1)],1)],1),e._v(" "),1==e.approveResult?r("el-form",{ref:"dept",attrs:{model:e.dept,rules:e.rules,"label-width":"80px"}},[r("el-form-item",{attrs:{label:"部门",prop:"sysDeptList"}},[r("el-select",{staticStyle:{width:"100%"},attrs:{filterable:"",multiple:"",placeholder:"请选择部门"},model:{value:e.dept.sysDeptList,callback:function(t){e.$set(e.dept,"sysDeptList",t)},expression:"dept.sysDeptList"}},e._l(e.deptList,(function(e,t){return r("el-option",{key:t,attrs:{label:e.deptName,value:e.deptId}})})),1)],1),e._v(" "),r("div",{staticStyle:{"text-align":"right"}},[r("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.onSubmitFinsh("dept")}}},[e._v("提交")])],1)],1):r("el-form",{ref:"carbon",attrs:{model:e.carbon,rules:e.rules,"label-width":"80px"}},[r("el-form-item",{attrs:{label:"审批人",prop:"nextApprove"}},[r("el-select",{staticStyle:{width:"100%"},attrs:{filterable:"",placeholder:"请选择下一级审批人"},model:{value:e.carbon.nextApprove,callback:function(t){e.$set(e.carbon,"nextApprove",t)},expression:"carbon.nextApprove"}},e._l(e.nextApproveList,(function(e){return r("el-option",{key:e.userId,attrs:{label:e.staffName,value:e.userId}})})),1)],1),e._v(" "),r("el-form-item",{attrs:{label:"抄送人",prop:"carbonList"}},[r("el-select",{staticStyle:{width:"100%"},attrs:{multiple:"",placeholder:"请选择抄送人"},model:{value:e.carbon.carbonList,callback:function(t){e.$set(e.carbon,"carbonList",t)},expression:"carbon.carbonList"}},e._l(e.nextApproveList,(function(e){return r("el-option",{key:e.userId,attrs:{label:e.staffName,value:e.userId}})})),1)],1),e._v(" "),r("div",{staticStyle:{"text-align":"right"}},[r("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.submitNextApprove("carbon")}}},[e._v("提交")])],1)],1)],1)],1)},o=[function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("p",{staticClass:"detail-title"},[r("span",[e._v("工单信息")])])},function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("p",{staticClass:"detail-title"},[r("span",[e._v("审批记录：")])])}],s=r("663e"),a=r("fe05"),n=r("c24f"),l={name:"work-foc-detail",data:function(){return{workOrderId:"",type:!1,activities:[],workOrderDetail:{},dialogVisible:!1,dialogVisibleSelectDept:!1,operateStatus:"",approve:{},deptList:[],dept:{sysDeptList:[],deptId:""},rules:{sysDeptList:[{required:!0,message:"请选择部门",trigger:"change"}],nextApprove:[{required:!0,message:"请选择审批人",trigger:"change"}],carbonList:[{required:!0,message:"请选择抄送人",trigger:"change"}],remark:[{required:!0,message:"请填写审批批注",trigger:"blur"}]},dialogVisibleNextApprove:!1,nextApproveList:[],approveResult:1,carbon:{nextApprove:"",carbonList:""}}},created:function(){this.workOrderId=this.$route.query.workOrderId,this.type=this.$route.query.type},mounted:function(){this.queryWorkDetail()},methods:{queryWorkDetail:function(){var e=this;Object(s["e"])({workOrderId:this.workOrderId}).then((function(t){e.$loading().close(),t.success&&(e.workOrderDetail=t.result,e.activities=JSON.parse(t.result.workNodeList),e.workOrderDetail.workInfo=JSON.parse(t.result.workInfo))}))},handlePreview:function(e){var t=decodeURI(e).lastIndexOf("."),r=decodeURI(e).substring(t+1),i="pdf swf html ott fodt  sxw doc docx rtf  wpd  txt  ods  ots  fods sxc  xls xlsx  csv  tsv  odp  otp fodp  sxi  ppt pptx  odg  otg fodg  svg  png jpg  tif  gif bmp";if(-1!=i.indexOf(r)){var o=decodeURI(e).substring(e.lastIndexOf("/")+1);if("xls xlsx".indexOf(r)>-1)window.open("".concat(window.location.protocol+"//"+window.location.host,"/electronic/pdf/documentConverterToPdf/").concat(o));else{var s=this.$router.resolve({path:"/pdfPreview",query:{filename:o,filetype:r}}),a=s.href;window.open(a,"_blank")}}else this.$message({showClose:!0,message:"该文件不支持预览，请下载后查看",type:"warning"})},goback:function(e){this.$router.push({path:this.$route.query.route,query:{activeName:this.$route.query.activeName}})},handle:function(e){this.dialogVisible=!0,this.operateStatus=e},onSubmit:function(e,t){var r=this;this.$refs[t].validate((function(t){if(!t)return console.log("error submit!!"),!1;r.workOrderDetail.nodeCount==r.workOrderDetail.workNode.nodeOrder&&"90"==e?(r.dialogVisibleSelectDept=!0,r.queryDeptList(),r.queryUserList()):r.approveSubmit(e)}))},queryDeptList:function(){var e=this;Object(a["c"])({pageNum:1,pageSize:1e3}).then((function(t){e.$loading().close(),t.success&&(e.deptList=t.result.result)}))},onSubmitFinsh:function(e){var t=this;this.$refs[e].validate((function(e){if(!e)return console.log("error submit!!"),!1;t.approveSubmit("90")}))},approveSubmit:function(e){for(var t=this,r=[],i=0;i<this.dept.sysDeptList.length;i++){var o={deptId:this.dept.sysDeptList[i]};r.push(o)}Object(s["a"])({workOrderId:this.workOrderDetail.workOrderId,currentNode:this.workOrderDetail.currentNode,workNode:{nodeId:this.workOrderDetail.workNode.nodeId,nodeOperateResult:e,nodeOperateDesc:this.approve.remark},sysDeptList:JSON.stringify(r)}).then((function(e){t.$loading().close(),e.success?(t.$message({type:"success",message:"操作成功"}),t.dialogVisibleSelectDept=!1,t.dialogVisible=!1,t.$router.go(-1)):t.$message({type:"fail",message:"操作失败"})}))},onSubmitNextApprove:function(){for(var e=this,t=[],r=0;r<this.carbon.carbonList.length;r++){var i={};i.userId=this.carbon.carbonList[r],t.push(i)}Object(s["b"])({workOrderId:this.workOrderDetail.workOrderId,currentNode:this.workOrderDetail.currentNode,workNode:{userId:this.carbon.nextApprove,nodeId:this.workOrderDetail.workNode.nodeId,nodeOperateResult:"90",nodeOperateDesc:this.approve.remark},workCarbonList:JSON.stringify(t)}).then((function(t){e.$loading().close(),t.success?(e.$message({type:"success",message:"操作成功"}),e.dialogVisible=!1,e.dialogVisibleNextApprove=!1,e.$router.go(-1)):e.$message({type:"fail",message:"操作失败"})}))},queryUserList:function(e){var t=this;Object(n["f"])({pageSize:1e4,pageNum:1},!1).then((function(e){e.success&&(t.nextApproveList=e.result.result)}))},submitNextApprove:function(e){var t=this;this.$refs[e].validate((function(e){if(!e)return console.log("error submit!!"),!1;console.log("success submit!!"),t.onSubmitNextApprove()}))}}},d=l,c=(r("4f76"),r("e90a")),p=Object(c["a"])(d,i,o,!1,null,"8528d784",null);t["default"]=p.exports},e347:function(e,t,r){},fe05:function(e,t,r){"use strict";r.d(t,"c",(function(){return o})),r.d(t,"d",(function(){return s})),r.d(t,"a",(function(){return a})),r.d(t,"b",(function(){return n}));var i=r("b775");function o(e){var t=!(arguments.length>1&&void 0!==arguments[1])||arguments[1];return Object(i["a"])({url:"/electronic/dept/queryDept",method:"post",loading:t,data:e})}function s(e){var t=!(arguments.length>1&&void 0!==arguments[1])||arguments[1];return Object(i["a"])({url:"/electronic/dept/updateDept",method:"post",loading:t,data:e})}function a(e){var t=!(arguments.length>1&&void 0!==arguments[1])||arguments[1];return Object(i["a"])({url:"/electronic/dept/addDept",method:"post",loading:t,data:e})}function n(e){var t=!(arguments.length>1&&void 0!==arguments[1])||arguments[1];return Object(i["a"])({url:"/electronic/deptElectronicDoc/queryDeptElectronicDoc",method:"post",loading:t,data:e})}}}]);