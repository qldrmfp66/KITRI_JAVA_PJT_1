const clockContainer = document.querySelector(".js-clock");
const clockTitle = clockContainer.querySelector("h1");
function getTime(){
	const date = new Date();
	const minutes = date.getMinutes();
	const hours = date.getHours();
	const seconds = date.getSeconds();
	clockTitle.innerText = `${hours < 10 ? `0${hours}` : hours
		}:${minutes < 10 ? `0${minutes}`: minutes
		}:${ seconds<10 ? `0${seconds}`: seconds }`; //html바꿔줌 - 초가 한자리면 앞에 0붙여서
}//getTime
function init(){
	getTime();
	setInterval(getTime,1000);	
}
//setInterval(function, 실행할 시간 간격)
init();
