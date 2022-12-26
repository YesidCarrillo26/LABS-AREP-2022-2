
let menuToggle = document.querySelector('.toggle');
            let menu = document.querySelector('.menu');
            menuToggle.onclick = function(){
                menu.classList.toggle('active')
                menuToggle.classList.toggle('active')
            }