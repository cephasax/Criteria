package domain;
import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Professor")
@SequenceGenerator(name="SEQ_PROFESSOR", initialValue=1, allocationSize=1, sequenceName="seq_professor")
public class Professor {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_PROFESSOR")
	private int idProfessor;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(	name="turma_professor",
				joinColumns = @JoinColumn(name="idProfessor"),
				inverseJoinColumns = @JoinColumn(name="idTurma"))
	private Collection<Turma> turmas;
	
	@Column
	private int matricula;
	
	@Column
	private String cpf;
	
	@Column
	private String nome;

	public int getIdProfessor() {
		return idProfessor;
	}

	public void setIdProfessor(int idProfessor) {
		this.idProfessor = idProfessor;
	}

	public Collection<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(Collection<Turma> turmas) {
		this.turmas = turmas;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
	
}
