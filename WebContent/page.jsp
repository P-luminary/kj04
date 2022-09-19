<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


<script type="text/javascript">
	 //首页 0  上一页 -1   下一页 1  尾页 2   Go  3
	 function formSbm(num){
		var  pageNo = document.getElementById("pageNo").value;
		if(num == 0){
			 document.getElementById("pageNo").value=1;
			}else if(num == -1){
				document.getElementById("pageNo").value
				= parseInt(pageNo)-1;
			}else if(num == 1){
				document.getElementById("pageNo").value
				= parseInt(pageNo)+1;
			}else if(num == 2){
				document.getElementById("pageNo").value=
					document.getElementById("totalPage").value;
			}else if(num == 3){
				var toNum = 
					parseInt(document.getElementById("toNum").value);
				
				if(isNaN(toNum)){//is not 数字 不是数字的话就去当前页面不动
					toNum=document.getElementById("pageNo").value;
				}else{
					var totalPage = 
						parseInt(document
						.getElementById("totalPage").value);
					if(toNum > totalPage){ //大于总页 去首页
						toNum = totalPage;
						
					}else if(toNum <=0){
						toNum=1;
					}
				}
					document.getElementById("pageNo").value=toNum;	
			}

		 document.getElementById("myForm").submit();
	 }

	</script>
</head>
<body>


<!--   首页 上一页 下一页 尾页  Go -->
     	<table width="100%" border="0px" align="center">
     		<tr>
     			<td>
     			<!-- 隐藏查询 -->
  <input type="hidden" name="pageNo" id="pageNo" value="${page.currentPage }"/>
  <input type="hidden" name="totalPage"  id="totalPage" value="${page.totalPage }"/>
     			
     			
     			<input type="button" value="首页" onclick="formSbm(0)" />
     			<c:if test="${page.currentPage >1 }">
     				<input type="button" value="上一页" onclick="formSbm(-1)" />
     			</c:if>
     			
     			<c:if test="${page.currentPage < page.totalPage }">
     				<input type="button" value="下一页" onclick="formSbm(1)" />
     			</c:if>
     			<input type="button" value="尾页" onclick="formSbm(2)" />
     			总记录数:${page.totalSize }条
     			每页显示${page.pageSize }条
     			共${page.totalPage }页 
     			当前第${page.currentPage }页
     			<input type="text" name="toNum" id="toNum"/>
     			<input type="button" value="GO" onclick="formSbm(3)"/>
     			
     			</td>
     		
     		
     		</tr>
     	
     	
     	</table>

</body>
</html>