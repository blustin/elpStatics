document.write("<link rel=\"stylesheet\" type=\"text/css\" href=\""+basePath+"/js/pagination/pagination.css\"/>"
		+ "<script type=\"text/javascript\" src=\""+basePath+"/js/pagination/jquery.pagination.js\"></script>");
var totalCount = 0, pageSize = 0, pageNo = 0;
// 初始化内容
function initPage(formId) {
  totalCount = parseInt($.trim($("#totalCount").val()));
  pageSize = parseInt($.trim($("#pageSize").val()));
  pageNo = parseInt($.trim($("#pageNo").val()));
  $("#page").pagination({
      totalCount : totalCount,
      pageSize : pageSize,
      currentPage : pageNo - 1,
      clickEvent : function(page_index) {
          $("#pageNo").val(page_index + 1);
          $("#pageSize").val(pageSize);
          if(formId)    $("#" + formId ).submit();
          else    $("#para").submit();
      }
  });
 }
