(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-741b14f3"],{"333d":function(t,e,o){"use strict";var n=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{staticClass:"pagination-container",class:{hidden:t.hidden}},[o("el-pagination",t._b({attrs:{background:t.background,"current-page":t.currentPage,"page-size":t.pageSize,layout:t.layout,"page-sizes":t.pageSizes,total:t.total},on:{"update:currentPage":function(e){t.currentPage=e},"update:current-page":function(e){t.currentPage=e},"update:pageSize":function(e){t.pageSize=e},"update:page-size":function(e){t.pageSize=e},"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}},"el-pagination",t.$attrs,!1))],1)},i=[];o("163d");Math.easeInOutQuad=function(t,e,o,n){return t/=n/2,t<1?o/2*t*t+e:(t--,-o/2*(t*(t-2)-1)+e)};var a=function(){return window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||function(t){window.setTimeout(t,1e3/60)}}();function l(t){document.documentElement.scrollTop=t,document.body.parentNode.scrollTop=t,document.body.scrollTop=t}function s(){return document.documentElement.scrollTop||document.body.parentNode.scrollTop||document.body.scrollTop}function c(t,e,o){var n=s(),i=t-n,c=20,r=0;e="undefined"===typeof e?500:e;var d=function t(){r+=c;var s=Math.easeInOutQuad(r,n,i,e);l(s),r<e?a(t):o&&"function"===typeof o&&o()};d()}var r={name:"Pagination",props:{total:{required:!0,type:Number},page:{type:Number,default:1},limit:{type:Number,default:20},pageSizes:{type:Array,default:function(){return[10,20,30,50]}},layout:{type:String,default:"total, sizes, prev, pager, next, jumper"},background:{type:Boolean,default:!0},autoScroll:{type:Boolean,default:!0},hidden:{type:Boolean,default:!1}},computed:{currentPage:{get:function(){return this.page},set:function(t){this.$emit("update:page",t)}},pageSize:{get:function(){return this.limit},set:function(t){this.$emit("update:limit",t)}}},methods:{handleSizeChange:function(t){this.$emit("pagination",{page:this.currentPage,limit:t}),this.autoScroll&&c(0,800)},handleCurrentChange:function(t){this.$emit("pagination",{page:t,limit:this.pageSize}),this.autoScroll&&c(0,800)}}},d=r,u=(o("e498"),o("e90a")),p=Object(u["a"])(d,n,i,!1,null,"6af373ef",null);e["a"]=p.exports},8201:function(t,e,o){},c344:function(t,e,o){"use strict";o.r(e);var n=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{staticClass:"app-container"},[o("el-row",{attrs:{gutter:20}},[o("el-col",{staticStyle:{"text-align":"left"},attrs:{span:12}},[o("el-input",{staticStyle:{width:"50%"},attrs:{placeholder:"搜索文件",size:"small"},model:{value:t.search,callback:function(e){t.search=e},expression:"search"}}),t._v(" "),o("el-button",{attrs:{type:"primary",size:"small"},on:{click:function(e){return t.querydocList(t.search)}}},[t._v("查询")])],1),t._v(" "),o("el-col",{staticStyle:{"text-align":"right"},attrs:{span:12}},[o("el-button",{attrs:{type:"primary",size:"small"},on:{click:t.uploaddoc}},[t._v("上传文件")])],1)],1),t._v(" "),o("el-table",{staticStyle:{width:"100%","margin-top":"30px"},attrs:{data:t.docList,border:"",size:"small"}},[o("el-table-column",{attrs:{type:"index",align:"center",label:"序号",width:"100px"}}),t._v(" "),o("el-table-column",{attrs:{align:"left","header-align":"center",label:"文件名称"},scopedSlots:t._u([{key:"default",fn:function(e){return["xls,xlsx,csv".indexOf(e.row.docType)>-1?o("svg-icon",{attrs:{"icon-class":"excel"}}):"ppt,pptx,pps,ppsx,pot".indexOf(e.row.docType)>-1?o("svg-icon",{attrs:{"icon-class":"PPT"}}):"pdf,dpt,odf".indexOf(e.row.docType)>-1?o("svg-icon",{attrs:{"icon-class":"PDF"}}):"doc,docx".indexOf(e.row.docType)>-1?o("svg-icon",{attrs:{"icon-class":"WORD"}}):"bmp,jpg,png,tif,gif,pcx,tga,exif,fpx,svg,psd,cdr,pcd,dxf,ufo,eps,ai,raw,WMF,webp,jpeg".indexOf(e.row.docType)>-1?o("svg-icon",{attrs:{"icon-class":"image"}}):o("svg-icon",{attrs:{"icon-class":"wendang"}}),t._v("\n        "+t._s(e.row.docName)+"\n      ")]}}])}),t._v(" "),o("el-table-column",{attrs:{align:"center",label:"修改时间"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n        "+t._s(e.row.operateTime)+"\n      ")]}}])}),t._v(" "),o("el-table-column",{attrs:{align:"center",label:"操作"},scopedSlots:t._u([{key:"default",fn:function(e){return[o("el-button",{attrs:{type:"primary",size:"small",icon:"el-icon-download"}},[o("a",{attrs:{href:e.row.docUrl,target:"_blank"}},[t._v("下载")])]),t._v(" "),o("el-button",{attrs:{type:"success",size:"small",icon:"el-icon-search"},on:{click:function(o){return t.handlePreview(e)}}},[t._v("预览")]),t._v(" "),o("el-button",{attrs:{type:"danger",size:"small",icon:"el-icon-delete"},on:{click:function(o){return t.deleteDoc(e)}}},[t._v("删除")])]}}])}),t._v(" "),o("div",{attrs:{slot:"empty"},slot:"empty"},[o("span",[t._v("未查询到数据")])])],1),t._v(" "),o("el-row",{attrs:{gutter:20}},[o("el-col",{staticStyle:{"text-align":"right"},attrs:{span:24}},[o("pagination",{attrs:{total:t.total,page:t.pageNum,limit:t.pageSize},on:{"update:page":function(e){t.pageNum=e},"update:limit":function(e){t.pageSize=e},pagination:t.getList}})],1)],1),t._v(" "),o("el-dialog",{attrs:{visible:t.dialogVisible,title:"新建文件"},on:{"update:visible":function(e){t.dialogVisible=e}}},[o("el-form",{ref:"ruleForm",staticClass:"demo-ruleForm",attrs:{"label-width":"100px"}},[o("el-form-item",[o("el-upload",{ref:"upload",staticClass:"upload-demo",attrs:{drag:"",action:t.action,"file-list":t.fileList,multiple:""}},[o("i",{staticClass:"el-icon-upload"}),t._v(" "),o("div",{staticClass:"el-upload__text"},[t._v("将文件拖到此处，或"),o("em",[t._v("点击上传")])]),t._v(" "),o("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[t._v("上传不超过100MB的文件")])])],1)],1),t._v(" "),o("div",{staticStyle:{"text-align":"right"}},[o("el-button",{attrs:{type:"danger",size:"small"},on:{click:function(e){t.dialogVisible=!1}}},[t._v("取消")]),t._v(" "),o("el-button",{attrs:{type:"primary",size:"small"},on:{click:function(e){return t.submitForm()}}},[t._v("提交")])],1)],1)],1)},i=[],a=(o("9a33"),o("e475")),l=o("333d"),s={components:{Pagination:l["a"]},data:function(){return{search:"",doc:{docName:"",docCode:""},docList:[],dialogVisible:!1,dialogType:"new",checkStrictly:!1,defaultProps:{children:"children",label:"title"},fileList:[],pageNum:1,pageSize:10,total:0,action:""}},mounted:function(){this.action=window.location.protocol+"//"+window.location.host+"/electronic/file/uploadFile",this.querydocList()},methods:{querydocList:function(t){var e=this;Object(a["d"])({pageSize:this.pageSize,pageNum:this.pageNum,docName:t}).then((function(t){e.$loading().close(),t.success&&(e.docList=t.result.result,e.total=t.result.count)}))},getList:function(){this.querydocList()},handleEdit:function(){this.fileList=[],this.dialogVisible=!0},handlePreview:function(t){var e=decodeURI(t.row.docUrl).lastIndexOf("."),o=decodeURI(t.row.docUrl).substring(e+1),n="pdf swf html ott fodt  sxw doc docx rtf  wpd  txt  ods  ots  fods sxc  xls xlsx  csv  tsv  odp  otp fodp  sxi  ppt pptx  odg  otg fodg  svg  png jpg  tif  gif bmp";if(-1!=n.indexOf(o)){var i=decodeURI(t.row.docUrl).substring(t.row.docUrl.lastIndexOf("/")+1);if("xls xlsx".indexOf(o)>-1)window.open("".concat(window.location.protocol+"//"+window.location.host,"/electronic/pdf/documentConverterToPdf/").concat(i));else{var a=this.$router.resolve({path:"/pdfPreview",query:{filename:i,filetype:o}}),l=a.href;window.open(l,"_blank")}}else this.$message({showClose:!0,message:"该文件不支持预览，请下载后查看",type:"warning"})},uploaddoc:function(){this.dialogType="new",this.dialogVisible=!0,this.doc={}},submitForm:function(){for(var t=this,e=[],o=this.$refs.upload.uploadFiles,n=0;n<o.length;n++)e.push({docName:o[n].response.result.fileName.split("&&")[1],docUrl:decodeURI(o[n].response.result.fileDownloadUri),docType:o[n].response.result.fileType,docRandomName:o[n].response.result.fileName});Object(a["b"])({electronicDocList:JSON.stringify(e)}).then((function(e){t.$loading().close(),e.success&&(t.$message({type:"success",message:"新增成功"}),t.fileList=[],t.dialogVisible=!1),t.pageNum=1,t.querydocList()}))},deleteDoc:function(t){var e=this;this.$confirm("确定删除吗?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){Object(a["c"])({docId:t.row.docId}).then((function(t){t.success?(e.$message({type:"success",message:"操作成功"}),e.pageNum=1,e.querydocList()):e.$message({type:"error",message:"提交失败"})}))}))}}},c=s,r=(o("fa07"),o("e90a")),d=Object(r["a"])(c,n,i,!1,null,"0a97e77a",null);e["default"]=d.exports},e475:function(t,e,o){"use strict";o.d(e,"d",(function(){return i})),o.d(e,"b",(function(){return a})),o.d(e,"a",(function(){return l})),o.d(e,"c",(function(){return s}));var n=o("b775");function i(t){var e=!(arguments.length>1&&void 0!==arguments[1])||arguments[1];return Object(n["a"])({url:"/electronic/doc/queryElectronicDoc",method:"post",loading:e,data:t})}function a(t){var e=!(arguments.length>1&&void 0!==arguments[1])||arguments[1];return Object(n["a"])({url:"/electronic/doc/addElectronicDoc",method:"post",loading:e,data:t})}function l(t){var e=!(arguments.length>1&&void 0!==arguments[1])||arguments[1];return Object(n["a"])({url:"/electronic/doc/addDeptElectronicDoc",method:"post",loading:e,data:t})}function s(t){var e=!(arguments.length>1&&void 0!==arguments[1])||arguments[1];return Object(n["a"])({url:"/electronic/doc/deleteElectronicDoc",method:"post",loading:e,data:t})}},e498:function(t,e,o){"use strict";var n=o("8201"),i=o.n(n);i.a},fa07:function(t,e,o){"use strict";var n=o("fe90"),i=o.n(n);i.a},fe90:function(t,e,o){}}]);