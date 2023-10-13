<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jQuery 라이브러리 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
	
	<h1>실습과제</h1>
	
	<h2>대한무역투자진흥공사_무역사기사례</h2>
	
	나라 :
	
	<select id="nation">
		<option>한국</option>
		<option>미국</option>
		<option>중국</option>
	</select>
	
	<input type="button" value="json" id="btn">
	<button id="btn2">xml</button>
	
	<h3>JSON 방식</h3>
	
	<table border="1" id="result">
		<thead>
			<tr>
				<th>발생국가</th>
				<th>발생시기</th>
				<th>사기유형</th>
				<th>제목</th>
				<th>내용</th>
			</tr>
		</thead>
		<tbody>
			
		</tbody>
	</table>
	
	<br>
	<hr>
	<br>
	
	<h3>XML 방식</h3>
	
	<div id="result2">
		
	</div>
	
	<script>
	
		$(() => {
		
			// json 방식
			$("#btn").click(() => {
				
				$.ajax({
					url:"fraud.do",
					data:{
						nation:$("#nation").val()
					},
					success:(data) => {
						
						console.log(data.response.body.itemList.item);
						
						const itemArr = data.response.body.itemList.item;
						
						let value = "";
						
						for(let i in itemArr) {
							
							value += "<tr>"
										+ "<td>" + itemArr[i].natn + "</td>"
										+ "<td>" + itemArr[i].othbcDt + "</td>"
										+ "<td>" + itemArr[i].fraudType + "</td>"
										+ "<td>" + itemArr[i].titl + "</td>"
										+ "<td>" + itemArr[i].bdtCntnt + "</td>"
									+ "</tr>"
							
						}
						
						$("#result tbody").html(value);
						
					},
					error:() => {
						console.log("ajax 통신 실패ㅠㅠ")
					}
				})
				
			})
			
			// xml 방식
			$("#btn2").click(() => {
				
				$.ajax({
					url:"fraud.do2",
					data:{
						nation:$("#nation").val()
					},
					success:(data) => {
						
						console.log($(data).find("item"));
						
						let $table = $("<table border='1'></table>");
						
						let $thead = $("<thead></thead>");
						let headTr =  "<tr>"
										+ "<th>발생국가</th>"
										+ "<th>발생시기</th>"
										+ "<th>사기유형</th>"
										+ "<th>제목</th>"
										+ "<th>내용</th>"
									+ "</tr>"
									
						//$thead.html(headTr);
									
						let $tbody = $("<tbody></tbody>");
						let bodyTr = "";			
						
						const itemArr = $(data).find("item");
						
						itemArr.each((i, item) => {
							
							bodyTr += "<tr>"
										+ "<td>" + $(item).find("natn").text() + "</td>"
										+ "<td>" + $(item).find("othbcDt").text() + "</td>"
										+ "<td>" + $(item).find("fraudType").text() + "</td>"
										+ "<td>" + $(item).find("titl").text() + "</td>"
										+ "<td>" + $(item).find("bdtCntnt").text() + "</td>"
									+ "</tr>"
							
						})
						
						//$tbody.html(bodyTr);
						
						$table.append($thead.append(headTr), $tbody.append(bodyTr))
							  .appendTo("#result2");
						// => 초기화가 안되고 자식요소로 계속 쌓이기 때문에 다른 국가를 선택하고 버튼을 다시 눌렀을 때 테이블이 밑에 이어져서 생성이 된다.
						
					},
					error:() => {
						console.log("ajax 통신 실패ㅠㅠ")
					}
				})
				
			})
				
		})
	
	</script>
	
</body>
</html>