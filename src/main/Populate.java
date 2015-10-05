package main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import control.Conexao;
import domain.*;


public class Populate{

	public static Conexao conex;
	
	public static void main(String[] args) throws Exception {
		
		conex = new Conexao();
		
		/*CURSOS 5*/
		Curso c1 = new Curso();
		c1.setNomeCurso("Ciencias da Computacao");
		c1.setCargaHoraria(3000);
		
		Curso c2 = new Curso();
		c2.setNomeCurso("Biscuit");
		c2.setCargaHoraria(2800);
		
		Curso c3 = new Curso();
		c3.setNomeCurso("Bacharelado em Tecnologia da Informacao");
		c3.setCargaHoraria(2500);
		
		Curso c4 = new Curso();
		c4.setNomeCurso("Letras apagadas");
		c4.setCargaHoraria(4500);
		
		Curso c5 = new Curso();
		c5.setNomeCurso("Ciencias ocultas");
		c5.setCargaHoraria(2200);
				
		/*PROFESSOR 4*/
		Professor p1 = new Professor();
		p1.setNome("Professor um");
		p1.setMatricula(19970001);
		p1.setCpf("052975845-21");
		
		Professor p2 = new Professor();
		p2.setNome("Professor dois");
		p2.setMatricula(29970002);
		p2.setCpf("052975845-22");

		Professor p3 = new Professor();
		p3.setNome("Professor tres");
		p3.setMatricula(19970003);
		p3.setCpf("052975845-23");

		Professor p4 = new Professor();
		p4.setNome("Professor quatro");
		p4.setMatricula(19970004);
		p4.setCpf("052975845-24");

		/*TURMA 5*/
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Turma t1 = new Turma();
		t1.setDataInicio(sdf.parse("15/04/2001"));
		t1.setDataTermino(sdf.parse("25/10/2001"));
		t1.setPeriodo("Noturno");
		t1.setSituacao(true);
		
		Turma t2 = new Turma();
		t2.setDataInicio(sdf.parse("15/04/2002"));
		t2.setDataTermino(sdf.parse("25/10/2002"));
		t2.setPeriodo("Noturno");
		t2.setSituacao(false);
		
		Turma t3 = new Turma();
		t3.setDataInicio(sdf.parse("15/04/2003"));
		t3.setDataTermino(sdf.parse("25/10/2003"));
		t3.setPeriodo("Noturno");
		t3.setSituacao(false);
		
		Turma t4 = new Turma();
		t4.setDataInicio(sdf.parse("15/04/2004"));
		t4.setDataTermino(sdf.parse("25/10/2004"));
		t4.setPeriodo("Vespertino");
		t4.setSituacao(false);
		
		Turma t5 = new Turma();
		t5.setDataInicio(sdf.parse("15/04/2015"));
		t5.setDataTermino(sdf.parse("25/10/2016"));
		t5.setPeriodo("Vespertino");
		t5.setSituacao(true);
		

		/*	TURMA << curso
		 *	TURMA << professor */
		
		ArrayList<Professor> profs = new ArrayList();
		
		//Turma 1
				
		t1.setCurso(c1);
		
		t1.setProfessores(new ArrayList<Professor>());
		t1.getProfessores().add(p1);
		t1.getProfessores().add(p2);
		
		//Turma 2
			
		t2.setCurso(c1);
		
		t2.setProfessores(new ArrayList<Professor>());
		t2.getProfessores().add(p3);
		
		//Turma 3
				
		t3.setCurso(c2);
		
		t3.setProfessores(new ArrayList<Professor>());
		t3.getProfessores().add(p1);
		t3.getProfessores().add(p4);
		
		//Turma 4
		t4.setCurso(c3);
		
		t4.setProfessores(new ArrayList<Professor>());
		t4.getProfessores().add(p1);
		t4.getProfessores().add(p3);
		t4.getProfessores().add(p4);
		
		//Turma 5
		t5.setCurso(c4);
		t5.setProfessores(new ArrayList<Professor>());
		t5.getProfessores().add(p1);
		t5.getProfessores().add(p4);
		
				
		/*
		 * CURSO << TURMA
		 * */
		
		//Curso 1
		c1.setTurmas(new ArrayList<Turma>());
		c1.getTurmas().add(t1);
		c1.getTurmas().add(t2);
		c1.getTurmas().add(t3);

		//Curso 2
		c2.setTurmas(new ArrayList<Turma>());
		c2.getTurmas().add(t1);
		c2.getTurmas().add(t2);

		//Curso 3
		c3.setTurmas(new ArrayList<Turma>());
		c3.getTurmas().add(t3);
		
		//Curso 4
		c4.setTurmas(new ArrayList<Turma>());
		c4.getTurmas().add(t4);
		c4.getTurmas().add(t5);

		//Curso 5
		c5.setTurmas(new ArrayList<Turma>());
		c5.getTurmas().add(t1);
		c5.getTurmas().add(t2);
		c5.getTurmas().add(t3);
		c5.getTurmas().add(t4);
		c5.getTurmas().add(t5);
		
		/*
		 * PROFESSORES << TURMA
		 * */
		
		//Professor 1
		p1.setTurmas(new ArrayList<Turma>());
		p1.getTurmas().add(t1);
		p1.getTurmas().add(t3);
		p1.getTurmas().add(t5);

		//Professor 2
		p2.setTurmas(new ArrayList<Turma>());
		p2.getTurmas().add(t1);
		
		//Professor 3
		p3.setTurmas(new ArrayList<Turma>());
		p3.getTurmas().add(t2);
		
		//Professor 4
		p4.setTurmas(new ArrayList<Turma>());
		p4.getTurmas().add(t3);
		p4.getTurmas().add(t5);
		
		
		
		
		conex.persistirObjeto(c1);
		conex.persistirObjeto(c2);
		conex.persistirObjeto(c3);
		conex.persistirObjeto(c4);
		conex.persistirObjeto(c5);
		
		conex.persistirObjeto(p1);
		conex.persistirObjeto(p2);
		conex.persistirObjeto(p3);
		conex.persistirObjeto(p4);
		
		conex.persistirObjeto(t1);
		conex.persistirObjeto(t2);
		conex.persistirObjeto(t3);
		conex.persistirObjeto(t4);
		conex.persistirObjeto(t5);

		conex.encerrrarConexao();
		
	}
}