package model;

import java.util.ArrayList;
import dao.AlunoDAO;

public class Aluno extends Pessoa {

    private String curso;
    private int fase;

    public Aluno() {
        this(0, "", 0, "", 0);
    }

    public Aluno(int id, String nome, int idade, String curso, int fase) {
        super(id, nome, idade);
        this.curso = curso;
        this.fase = fase;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getFase() {
        return fase;
    }

    public void setFase(int fase) {
        this.fase = fase;
    }

    @Override
    public String toString() {
        return super.toString() + "curso=" + curso + ", fase=" + fase;
    }

    public ArrayList<Aluno> getMinhaLista() {
        return AlunoDAO.getMinhaListaMemoria();
    }

    public boolean insertAlunoBD(String nome, int idade, String curso, int fase) {
        int id = this.maiorID() + 1;
        Aluno objeto = new Aluno(id, nome, idade, curso, fase);
        getMinhaLista().add(objeto);
        return true;
    }

    public boolean deleteAlunoBD(int id) {
        int indice = this.procuraIndice(id);
        getMinhaLista().remove(indice);
        return true;
    }

    public boolean updateAlunoBD(int id, String nome,
            int idade, String curso, int fase) {
        Aluno objeto = new Aluno(id, nome, idade, curso, fase);
        int indice = this.procuraIndice(id);
        getMinhaLista().set(indice, objeto);
        return true;
    }

    private int procuraIndice(int id) {
        int indice = -1;
        for (int i = 0; i < getMinhaLista().size(); i++) {
            if (getMinhaLista().get(i).getId() == id) {
                indice = i;
            }
        }
        return indice;
    }

    public Aluno carregaAluno(int id) {
        int indice = this.procuraIndice(id);
        return getMinhaLista().get(indice);
    }

    public int maiorID() {
        return AlunoDAO.maiorIDLista();
    }
}
