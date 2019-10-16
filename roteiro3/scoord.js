exports.disciplina = disciplina;

function disciplina(_id,_nome,_creditos,_pre_requisitos){
    let nome = _nome;  
    let id = _id;

    return{
        creditos : _creditos,
        pre_requisitos : _pre_requisitos,
        get_nome :() => nome,
        set_nome : function(novoNome) {nome = novoNome},
        id : () => id
       }
}

function turma(_id_disciplina, _periodo){
    let id_disciplina = ref_disciplina;
    let periodo = _periodo;
    let status = "planejada";
    let professor = null;
    let estudates = [];
    return{
        set_status : function (novoStatus) {
            if(novoStatus != "planejada"){
              return "status invalido"; 
            }
            status = novoStatus;
            return "Status Atualizado";
            
        },
        get_professor: () => professor,
        get_estudantes: () => estudates,
        get_disciplina : () => id_disciplina,
        get_status : () => status,
        get_periodo : () => periodo,
        matricular_aluno : (novoAluno) => "codigo aqui"
    }    
}





let d = disciplina("prog","programa1",4,[]);
console.log(d.get_nome());