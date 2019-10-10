console.log('app rodando!');


let disciplinas_lista = [];
let URL = 'https://lab01-projsw-ufcg.herokuapp.com/api/disciplinas';

window.delete_disciplina = delete_disciplina;


function fecth_disciplinas(){
	fetch(URL)
	.then(response => response.json())
	.then(dados => { 
    disciplinas_lista = dados;
    show();
   
    
});
}



function add_disciplina(){
    let nome = document.querySelector("#nome").value;
    let nota = document.querySelector("#nota").value;

    fetch(URL, {
      'method': 'POST',
      'body': `{"nome": "${nome}", "nota": ${nota}}`,
      'headers': {'Content-Type': 'application/json'}
    })
    .then(response => response.json)
    .then(dados => {
        console.log(dados);
        fecth_disciplinas();
    })
}
function delete_disciplina(id){
    fetch(URL + '/' + id,{
        'method' : 'DELETE'
    })
    .then(r => r.json)
    .then(dados =>{
        console.log(dados);
        fecth_disciplinas();
    });
}





function show(){
	let $disciplinas =  document.querySelector("#tabela")
    $disciplinas.innerHTML = '';
    disciplinas_lista.forEach((elemento,i) => {
        let $p = document.createElement("p");
        $p.innerHTML =  '<div class = "left"><button class = "delete" id = "' + disciplinas_lista[i].id + '"  style= "align-self: center" > DEL </button></div><p>  Nome:  ' +  disciplinas_lista[i].nome + '  Nota: ' + disciplinas_lista[i].nota + '</p><p> Id: ' + disciplinas_lista[i].id + '</p>';
        $disciplinas.appendChild($p);
        
    });
    let $buttons = document.getElementsByClassName("delete");
    for (let index = 0; index < disciplinas_lista.length; index++) {
        $buttons[index].addEventListener('click',_ => {
            delete_disciplina($buttons[index].id)
        });
    }
}

function set_atributo_nome(id,nome){
    fetch(URL + '/' + id + '/nome',{
        'method' : 'PUT',
        'body': `{"nome" : "${nome}"}`,
        'headers' : {'Content-Type' : 'application/json'}
    })
    .then(response => response.json)
    .then(dados => {
        console.log(dados);
    })
}

function set_atributo_nota(id,nota){
    fetch(URL + '/' + id + '/nota',{
        'method' : 'PUT',
        'body': `{"nota":"${nota}"}`,
        'headers' : {'Content-Type':'application/json'}
    });

}

function alterar_disciplina(){
    let $nome = document.querySelector("#novo_nome").value;
    let $nota = document.querySelector("#nova_nota").value;
    let $id = document.querySelector("#id").value;
    if($nome != '' && $nota != '' && $id != ''){
        set_atributo_nome($id,$nome);
        set_atributo_nota($id,$nota);
        fecth_disciplinas();
        console.log("tudo preenchido")
    }else if($nome != '' && $id != ''){
        set_atributo_nome($id,$nome);
        fecth_disciplinas();
        console.log("nome preenchido")
    }else if($nota != '' && $id != '' ){
        set_atributo_nota($id,$nota);
        fecth_disciplinas();
        console.log("nota preenchido")
    }else{
        console.log("dados invalidos")
    }


}
    
    
	




(function init(){
    fecth_disciplinas();
    let $button_send = document.getElementById("send");
    let $button_set = document.getElementById("set");
    $button_send.addEventListener('click',add_disciplina);
    $button_set.addEventListener('click',alterar_disciplina);
}());



