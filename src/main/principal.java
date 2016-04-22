package main;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import control.Conexao;
import domain.*;

public class principal {

	public static Conexao conex;
	
	public static void main(String[] args) {
		
		conex = new Conexao();
		
		CriteriaBuilder builder = conex.getEm().getCriteriaBuilder();
		
		//CONSULTA 1
		//Todos os cursos com ch > 3600
		
		CriteriaQuery<Curso> query = builder.createQuery(Curso.class);
		Root<Curso> curso = query.from(Curso.class);
		Predicate pred = builder.gt(curso.get("cargaHoraria"), 3600);
		
		query.select(curso);
		
		query.where(pred);
		TypedQuery<Curso> tpQuery = conex.getEm().createQuery(query);
		List<Curso> CursoList = tpQuery.getResultList();
		
		
		System.out.println();
		System.out.println("***************************************");
		System.out.println("Todos os cursos com carga horaria maior que 3600:");
		System.out.println();

		for(Curso c: CursoList){
			System.out.println(c.getNomeCurso() + " = Carga horaria: " + c.getCargaHoraria());
		}
		System.out.println("---------------------------------------");
		
		//CONSULTA 2
		//Todos as turmas do periodo da tarde e noite
		
		CriteriaQuery<Turma> criteriaQuery2 = builder.createQuery(Turma.class);
		Root<Turma> turma = criteriaQuery2.from(Turma.class);
		Predicate pred1 = builder.equal(turma.get("periodo"), "Noturno");
		Predicate pred2 = builder.equal(turma.get("periodo"), "Vespertino");
		
		Predicate p1orP2 = builder.or(pred1, pred2); 
				
		criteriaQuery2.select(turma);
		
		criteriaQuery2.where(p1orP2);
		TypedQuery<Turma> query2 = conex.getEm().createQuery(criteriaQuery2);
		List<Turma> turmasConsulta2 = query2.getResultList();

		System.out.println();
		System.out.println("***************************************");
		System.out.println("Todos as turmas do periodo vespertino e noturno:");
		System.out.println();

		for(Turma turm: turmasConsulta2){
			System.out.println("Turma: " + turm.getIdTurma()
								+ " - data de inicio: " + turm.getDataInicio() 
								+ " - data de termino: " + turm.getDataTermino());
		}
				
		System.out.println("---------------------------------------");
		
		//CONSULTA 3
		//todas as turmas com situação = "Aberta" e	curso = "Ciência da Computação“;
		
		CriteriaQuery<Turma> criteriaQuery3 = builder.createQuery(Turma.class);
		Root<Turma> turma2 = criteriaQuery3.from(Turma.class);
		Join<Turma, Curso> cursoJoin = turma2.join("curso");
		
		Predicate pred3 = builder.equal(turma2.get("situacao"), true);
		Predicate pred4 = builder.equal(cursoJoin.get("nomeCurso"), "Ciencias da Computacao");
		Predicate p3andP4 = builder.and(pred3, pred4); 		
		criteriaQuery3.select(turma2);
		criteriaQuery3.where(p3andP4);
		
		TypedQuery<Turma> query3 = conex.getEm().createQuery(criteriaQuery3);
		List<Turma> turmasConsulta3 = query3.getResultList();

		System.out.println();
		System.out.println("***************************************");
		System.out.println("Todos as turmas abertas do curso Ciencias da Computacao:");
		System.out.println();
		for(Turma turm: turmasConsulta3){
			System.out.println("Turma: " + turm.getIdTurma()
								+ ", Curso: " + turm.getCurso().getNomeCurso()
								+ " - data de inicio: " + turm.getDataInicio() 
								+ " - data de termino: " + turm.getDataTermino());
		}
		System.out.println("---------------------------------------");
		
		//CONSULTA 4
		//todos os professores que lecionam no curso de "Bacharelado em Tecnologia da Informação“;
		CriteriaQuery<Professor> c4 = builder.createQuery(Professor.class);
		
		Root<Professor> prof = c4.from(Professor.class);
				
		Join<Professor, Turma> profTurma = prof.join("turmas");
		Join<Turma, Curso> turmaCurso = profTurma.join("curso");
		
		Predicate p1 = builder.equal(turmaCurso.get("nomeCurso"), "Bacharelado em Tecnologia da Informacao");
			
		c4.select(prof);
		c4.where(p1);
		
		TypedQuery<Professor> q4 = conex.getEm().createQuery(c4);
		List<Professor> profsC4 = q4.getResultList();

		System.out.println();
		System.out.println("***************************************");
		System.out.println("Todos os professores do Bacharelado em Tecnologia da Informação");
		System.out.println();
		for(Professor prof4: profsC4){
			System.out.println(	"Professor: " + prof4.getNome()
								 + ", matricula: " + prof4.getMatricula());
		}		
		System.out.println("---------------------------------------");

		
		//CONSULTA 5
		//todos os  professores de turmas com periodo = Tarde e com cursos de carga horária > 4000
		CriteriaQuery<Professor> c5 = builder.createQuery(Professor.class);
		
		Root<Professor> prof5 = c5.from(Professor.class);
				
		Join<Professor, Turma> profTurma5 = prof5.join("turmas");
		Join<Turma, Curso> turmaCurso5 = profTurma5.join("curso");
		
		Predicate p1c5 = builder.equal(profTurma5.get("periodo"), "Vespertino");
		Predicate p2c5 = builder.gt(turmaCurso5.get("cargaHoraria"), 4000);
		
		Predicate pgeral = builder.and(p1c5, p2c5); 
		
		c5.select(prof5);
		c5.where(pgeral);
		c5.distinct(true);
		
		TypedQuery<Professor> q5 = conex.getEm().createQuery(c5);
		List<Professor> profsC5 = q5.getResultList();

		System.out.println();
		System.out.println("***************************************");		
		System.out.println("Todos os professores de turmas com periodo = Tarde e com cursos de carga horária > 4000");
		System.out.println();
		for(Professor p5: profsC5){
			System.out.println(	"Professor: " + p5.getNome()
								 + ", matricula: " + p5.getMatricula());
		}
		System.out.println("---------------------------------------");

		
		/*		
		http://www.objectdb.com/java/jpa/query/jpql/logical
		*/	
	}

}
