<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <!--  Title  -->
    <title>WherMoneyGoo</title>
    
    <!-- Avoid zoom-in and out from mobile view-->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    
    <!-- Latest compiled and minified CSS for bootstrap-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
    <!-- icon lib-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
    <!-- icon lib-->
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    
    <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.15/css/jquery.dataTables.css">
  
    <script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.15/js/jquery.dataTables.js"></script>
    
    <link rel="stylesheet" href="css/StandBy.css" type="text/css"/>
    
    <script src="js/StandBy.js"></script>
    
</head>
<body>
    
    <section id="top">
      <div class="container text-center">
        <div><h3>Available Budget In <span id="avdate">26 July 2017</span></h3></div> 
        <div><h2><span id="avamount">+ 2,345.64</span></h2></div> 
        <div>Income <span id="income">5,000.00</span></div> 
        <div>Expenses <span id="expenses">5,000.00</span></div> 
      </div>
    </section>
    
    <section id="middle">
    <form id="detail-form" class="form-inline"role="form">
    <div class="container text-center">
        
    <div class="form-group">
      <select class="form-control" id="symbol" name="symbol">
        <option selected disabled>Select One</option>
        <option value="0">-</option>
        <option value="1">+</option>
      </select>
    </div>   
    
    <div class="form-group">
      <input type="text" class="form-control" id="description" name="description" placeholder="Description">
    </div>
        
    <div class="form-group">
      <input type="text" class="form-control" id="amount" name="amount" placeholder="Amount">
    </div>
        
    <div id="sbtn" class="form-group">
      <a class="form-control glyphicon glyphicon-play-circle btn-info" id="submit-btn"></a>
    </div>
        
    </div>
        
    </form>

    
    </section>
    
    <section id="bot" class="container text-center">
    <div class="col-md-6">
    <div id="tb-income" class="text-center">
    <h3>Icome</h3>
    
    <form>
    <table id="income-table" class="table table-striped table-hover">
        <thead>
        <tr>
          <th class="text-center">ID.</th>    
          <th class="text-center">Description</th>    
          <th class="text-center">Amount</th>     
          <th class="text-center">Date</th>     
          <th class="text-center">Action</th>     
        </tr>   
        </thead>
        <tbody>
          <c:forEach var="temp" items="${LIST_DETAIL}" >
          <!-- Link for list of details -->
          <tr>
              <td>${temp.detail_id}</td>
              <td>${temp.description}</td>
              <td>${temp.amount}</td>
              <td>${temp.date}</td>
              <td><a class="glyphicon glyphicon glyphicon-minus income-remove" name="REMOVE" value="${temp.detail_id}"></a></td>                
          </tr>
          </c:forEach>    
        </tbody>
    </table>
    </form>
    </div>
    </div>
      <script>
      
      $(".income-remove").click(function(){
          console.log("Income Remove");

          /*
           * Ajax Post method
           */
          var description = $("#description").val();
     	  var incomeId = $(this).attr("value");
          //JSON Format
          var toSer = {  //Data that need to be send to server
            command: "REMOVE",
            incomeId: incomeId
            };
          		  
           $.ajax({
          	 
            type: "Get", //Method Used
            
            url:"DetailController", //API Path
            
            data:toSer, //Data that need to be send to server
                      
            success:function(data) {
          	  window.location.replace('/WherMoneyGoo/DetailController');
            },
            error:function(textStatus, errorThrown) {
              console.log(textStatus);//Display error in console log
            }
          });
        });

		
      
      $("#submit-btn").click(function(e){
        console.log("Form");
        /*
         * Ajax Post method
         */
		 e.preventDefault();
         var description = $("#description").val();
         var amount = $("#amount").val();
         var symbol = $("#symbol").val();
		                  
        //JSON Format
        var toSer = {  //Data that need to be send to server
          command: "ADD",
          symbol: symbol,
          description:description,
          amount:amount,
          date:callTodayDate()
          };
        		  
         $.ajax({
        	 
          type: "Post", //Method Used
          
          url:"DetailController", //API Path
          
          data:toSer, //Data that need to be send to server
                    
          success:function(data) {
        	  window.location.replace('/WherMoneyGoo/DetailController');
          },
          error:function(textStatus, errorThrown) {
            console.log(textStatus);//Display error in console log
          }
        });
      });
      
      $(document).ready(function() {
    	  callTodayDate();
          var income = $('#income-table').DataTable({
        	  "searching": false
          });
          var expense =  $('#expense-table').DataTable({
        	  "searching": false
          });
      } );
    </script>
    <div class="col-md-6">
    <div id="tb-expense"class="text-center">
    <h3>Expenses</h3>
      <table id="expense-table" class="table table-striped table-hover">
        <thead>
        <tr>
          <th class="text-center">ID.</th>    
          <th class="text-center">Description</th>    
          <th class="text-center">Amount</th>     
          <th class="text-center">Date</th>     
          <th class="text-center">Action</th>     
        </tr>    
        </thead>
        <tbody>
          <c:forEach var="temp" items="${LIST_EXPENSE}" >
          <!-- Link for list of details -->
          <tr>
              <td>${temp.detail_id}</td>
              <td>${temp.description}</td>
              <td>${temp.amount}</td>
              <td>${temp.date}</td>
              <td><a class="glyphicon glyphicon glyphicon-minus income-remove" name="REMOVE" value="${temp.detail_id}"></a></td>                
          </tr>
          </c:forEach> 
        </tbody>
      </table>
    </div>    
    </div>    
        
    </section>
</body>
</html>
