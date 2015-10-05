package domain;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Turma")
@SequenceGenerator(name="SEQ_TURMA", initialValue=1, allocationSize=1, sequenceName="seq_turma")
public class Turma implements Serializable{

	@OneToOne
	private Curso curso;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(	name="turma_professor",
				joinColumns = @JoinColumn(name="idTurma"),
				inverseJoinColumns = @JoinColumn(name="idProfessor"))
	private Collection<Professor> professores;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_TURMA")
	private int idTurma;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date dataInicio;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date dataTermino;
	
	@Column
	private String periodo;
	
	@Column
	private boolean situacao;

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Collection<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(Collection<Professor> professores) {
		this.professores = professores;
	}

	public int getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(int id) {
		this.idTurma = id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public boolean isSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}
}
