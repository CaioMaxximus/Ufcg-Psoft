let assert = require('assert');
let scoord = require('./scoord');
let disciplina = scoord.disciplina;
let turma = scoord.turma;
let estudante = scoord.estudante;
let professor = scoord.professor 

describe('factory Disciplina', function() {
  let d0;

    before(async () => {
        d0 = disciplina('prog1', 'Programação 1', 4, []);
    })

    it('deve criar disciplinas distintas a cada invocação', function(){
        d0 = disciplina('prog1', 'Programação 1', 4, []);
        d1 = disciplina('prog1', 'Programação 1', 4, []);
        d2 = disciplina('prog1', 'Programação 1', 4, []);
        assert.notEqual(d0, d1);
        assert.notEqual(d0, d2);
        assert.notEqual(d1, d2);
    });

    it('deve reter os dados de inicialização', function(){
        assert.equal('prog1', d0.id());
        assert.equal('Programação 1', d0.get_nome());
        assert.equal(4, d0.creditos);
        assert.deepEqual([], d0.pre_requisitos);
    });

    it('deve permitir atualização de nome', function(){
        d0.set_nome('Programação de Computadores I')
        assert.equal('prog1', d0.id());
        assert.equal('Programação de Computadores I', d0.get_nome());
        assert.deepEqual([], d0.pre_requisitos);
    });

    it('não deve permitir atualização de id via set_id', function(){
        assert.throws(function () {
            d0.set_id('outro')
        }, TypeError);
        assert.equal('prog1', d0.id());
    });

});


describe('factory turma',function (){
    let d0;
    let d1;
    let t0;
    let e0;

    before(async () => {
        d0 = disciplina('calc1',"calculo1",4,[]);
        d1 = disciplina("calc2",'calculo2',4,[]);
        e0 = estudante("caio","117210","@seila","123");
        t0 = turma(d0,"2");
    })

    it('deve criar turmas distintas ',function(){
        let t1 = turma(d0,"2");
        let t2 = turma(d1,"3");
        assert.notEqual(t0,t1);
        assert.notEqual(t0,t2);
        assert.notEqual(t1,t2);
    });

    it('dados de inicializacao corretos',function(){
        assert.equal(t0.get_disciplina(),d0);
        assert.equal(t0.get_periodo(),"2");
        assert.equal(t0.get_status(),'planejada');
        assert.equal(t0.get_professor(),null);
        assert.deepEqual(t0.get_estudantes(),[]);
    });

    it('restricoes na matricula de um estudante e status da disciplina',function(){
        let t1 = turma(d1,"3");
        assert.equal(t0.set_status("em_espera"),"Status Inválido");
        assert.equal(t0.set_status("concluída"),"Status Atualizado");
        assert.equal(t0.matricular_aluno(e0),"Matricula Nao Realizada");
        assert.equal(t1.matricular_aluno(e0),"Matricula Realizada");
        assert.deepEqual(t1.get_estudantes()[0],e0);
        assert.equal(t1.matricular_aluno(e0),"Matricula Nao Realizada");
        t1.desmatricular_aluno(e0);
        assert.deepEqual(t1.get_estudantes(),[]);        
    })
});


describe('factory professor', function(){
    let p0;
    
    before(async () => {
        p0 = professor("123","jerson","@gmail","321-1","www");

    });

    it('deve criar professores distintos',function(){
        p1 = professor("123","jerson","@gmail","321-1","www");
        assert.notEqual(p0,p1);
    });

    it ('dados de inicializacao corretos',function(){
        assert.equal(p0.get_nome(),"jerson");
        assert.equal(p0.get_matricula(),"123");
        assert.equal(p0.get_email(),"@gmail");
        assert.equal(p0.get_cpf(),"321-1");
        assert.equal(p0.get_foto(),"www");
    });

    it('deve ser vinculado a uma ou mais turmas',function(){
        let d0 = disciplina('prog1', 'ProgramaÃ§Ã£o 1', 4, []);
        let t0 = turma(d0,"1");
        p0.aloca_turma(t0);
        assert.equal(p0.turmas("1")[0],d0);
        assert.equal(t0.get_professor(),p0);
    })

    it('nao deve permitir setar nem o nome nem a matricula',function(){
        assert.throws(function () {
            p0.set_nome('outro');
            p0.set_matricula("2001");
        }, TypeError);
        assert.equal(p0.get_nome(),"jerson");
        assert.equal(p0.get_matricula(),"123");
    })
});


describe('factory estudante',function(){
    let e0;

    before(async () =>{
        e0 = estudante("caio","117210","@seila","123");
    });
    it('deve criar estudantes distintos',function(){
        e1 = estudante("caio","117210","@seila","123");
        e2 = estudante("caio","117210","@seila","123");
        assert.notEqual(e0,e1);
        assert.notEqual(e1,e2);
        assert.notEqual(e2,e0);
    });

    it('dados de inicializacao corretos',function(){
        assert.equal(e0.get_nome(),"caio");
        assert.equal(e0.get_matricula(),"117210");
        assert.equal(e0.get_email(),"@seila");
        assert.equal(e0.get_cpf(),"123");


    });

    it('deve poder se matricular em varias turmas ',function(){
        d0 = disciplina('calc1',"calculo1",4,[]);
        d1 = disciplina('calc2',"calculo2",4,[]);
        t0 = turma(d0,"2");
        t1 = turma(d1,"3");
        e0.matricula(t0);
        e0.matricula(t1);
        assert.equal(e0.turmas("2")[0],d0);
        assert.equal(e0.turmas("3")[0],d1);

    })
});


