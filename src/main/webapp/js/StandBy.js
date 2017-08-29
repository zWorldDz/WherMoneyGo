function callTodayDate(){
          var today = new Date();
          var dd = today.getDate();
          var mm = today.getMonth()+1; //January is 0!
          var yyyy = today.getFullYear();
          if(dd<10){
              dd='0'+dd;
          } 
          if(mm<10){
              mm='0'+mm;
          } 
          today = dd+'/'+mm+'/'+yyyy;//mm
          return mm;//mm
      }

function changeNumtoCharMonth(mm){
	console.log(mm);
	var mmChar;
	if(mm == "01"){
		mmChar = "January";
	}else if(mm == "02"){
		mmChar = "February";
	}else if(mm == "03"){
		mmChar = "March";
	}else if(mm == "04"){
		mmChar = "April";
	}else if(mm == "05"){
		mmChar = "May";
	}else if(mm == "06"){
		mmChar = "June";
	}else if(mm == "07"){
		mmChar = "July";
	}else if(mm == "08"){
		mmChar = "August";
	}else if(mm == "09"){
		mmChar = "September";
	}else if(mm == "10"){
		mmChar = "October";
	}else if(mm == "11"){
		mmChar = "November";
	}else if(mm == "12"){
		mmChar = "December";
	}
	return mmChar;
}

function htmlUpdate(){
	var mm = callTodayDate();
	$("#avdate").html(changeNumtoCharMonth(mm));
}

$(document).ready(function(){
	htmlUpdate();
	});
