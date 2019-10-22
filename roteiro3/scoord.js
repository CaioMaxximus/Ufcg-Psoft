exports.disciplina = disciplina;
exports.turma = turma;
exports.estudante = estudante;
exports.professor = professor;
let assert = require('assert');


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

function turma(_disciplina, _periodo){
    let disciplina = _disciplina;
    let periodo = _periodo;
    let status = "planejada";
    let professor = null;
    let estudantes = [];
    let statusValido = function(status_novo){
        if(status_novo != "planejada" &&  status_novo != "ativa" && status_novo != "concluída"){
            return false;
        }
        return true;
    };
    let matricula_novo = function(_aluno){
        if(!estudantes.includes(_aluno) && (status == "planejada" || status == "ativa") ){
            estudantes.push(_aluno)
            return "Matricula Realizada";
        };
        return "Matricula Nao Realizada";
    };
    return{
        set_status : function (novoStatus) {
            if(!statusValido(novoStatus)){
              return "Status Inválido"; 
            }
            status = novoStatus;
            return "Status Atualizado";
            
        },
        get_professor: () => professor,
        get_estudantes: () => estudantes,
        get_disciplina : () => disciplina,
        get_status : () => status,
        get_periodo : () => periodo,
        set_professor : (_professor) => professor = _professor,
        matricular_aluno : (_aluno) => matricula_novo(_aluno),
        desmatricular_aluno: (_aluno) => 
            estudantes = estudantes.filter(function(value){
                return !(value === _aluno);
            
            })
    }    
}

function professor(_matricula, _nome,_email,_cpf,_url_foto){
    let matricula = _matricula;
    let nome = _nome;
    let email = _email;
    let cpf = _cpf;
    let turmas = [];
    let url_foto = _url_foto;
    return{
        get_nome : () => nome,
        get_email : () => email ,
        get_cpf : () => cpf,
        get_foto : () => url_foto,
        set_foto : (_url_foto) => url_foto = _url_foto,
        get_matricula : () => matricula,
        aloca_turma: function(turma) { turma.set_professor(this); turmas.push(turma)},
        turmas: function (semestre) {
                    let saida = [];
                    turmas.forEach(turma => {
                        if (turma.get_periodo() == semestre){
                            saida.push(turma.get_disciplina());
                        }
                    });
                    return saida;
                },
         

    }
}


function estudante(_nome,_matricula,_email,_cpf){
    let nome = _nome;
    let matricula = _matricula;
    let email = _email;
    let cpf = _cpf;
    let url_foto = "Sem Foto";
    let turmas = [];
    return {
        get_nome : () => nome,
        set_nome : (novo_nome) => nome = novo_nome,
        matricula: (turma) => turma.matricular_aluno(this),
        get_email : () => email ,
        get_cpf : () => cpf,
        get_foto : () => url_foto,
        get_matricula: () => matricula,
        matricula : (turma) => {turmas.push(turma),turma.matricular_aluno(this)}, 
        turmas : (semestre) => {
            let saida = [];
            turmas.forEach(turma => {
                if (turma.get_periodo() == semestre){
                    saida.push(turma.get_disciplina());
                }
            });
            return saida;
        },
    };
}




// let d = disciplina("prog","programa1",4,[]);
// let t0 = turma(d ,"2");
// let e0 = estudante("caio","117210","@gmail.com","123123-1");
// t0.matricular_aluno(e0);
// let p0 = professor("123","einstein","@gmail","11-11");
// p0.aloca_turma(t0);
// console.log(p0.turmas("2"));
// console.log(d === d);

