package domain;
import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Curso")
@SequenceGenerator(name="SEQ_CURSO", initialValue=1, allocationSize=1, sequenceName="seq_curso")
public class Curso {

	@OneToMany(mappedBy="curso")
	private Collection<Turma> turmas;
		
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_CURSO")
	private int idCurso;
	
	@Column
	private String nomeCurso;
	
	@Column
	private int cargaHoraria;

	public Collection<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(Collection<Turma> turmas) {
		this.turmas = turmas;
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nome) {
		this.nomeCurso = nome;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	
}
