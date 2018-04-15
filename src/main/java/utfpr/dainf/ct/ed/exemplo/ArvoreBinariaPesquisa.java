package utfpr.dainf.ct.ed.exemplo;
import java.util.ArrayDeque;
import java.util.Stack;


/**
 * UTFPR - Universidade TecnolÃ³gica Federal do ParanÃ¡
 * DAINF - Departamento AcadÃªmico de InformÃ¡tica
 * 
 * Exemplo de implementaÃ§Ã£o de Ã¡rvore binÃ¡ria de pesquisa.
 * @author Wilson Horstmeyer Bogado <wilson@utfpr.edu.br>
 * @param <E> O tipo do valor armazenado nos nÃ³s da Ã¡rvore
 */
public class ArvoreBinariaPesquisa<E extends Comparable<E>> extends ArvoreBinaria<E> {
    public ArvoreBinariaPesquisa<E> pai;
    protected Stack<ArvoreBinariaPesquisa<E>> pilha;
    protected ArvoreBinariaPesquisa<E> esquerda;
    protected ArvoreBinariaPesquisa<E> direita;
    protected ArvoreBinariaPesquisa<E> ultimoVisitado;
    
    
    // para percurso iterativo
    private boolean inicio = true;
    private ArvoreBinariaPesquisa<E> noPos;

    
    private void inicializaPilha() {
        if (pilha == null) {
            pilha = new Stack<>();
        }
    }
    
    public void reinicia() {
        inicializaPilha();
        pilha.clear();
        ultimoVisitado = this;
        inicio = true;
    }
    
    /**
     * Retorna a Ã¡rvore esqueda.
     * @return A Ã¡rvore esquerda.
     */
    protected ArvoreBinaria<E> getEsquerda() {
        return esquerda;
    }

    /**
     * Retorna a Ã¡rvore direita.
     * @return A Ã¡rvore direita.
     */
    protected ArvoreBinaria<E> getDireita() {
        return direita;
    }

    /**
     * Inicializa a Ã¡rvore esquerda.
     * @param esquerda A Ã¡rvore esquerda.
     */
    protected void setEsquerda(ArvoreBinariaPesquisa<E> esquerda) {
        this.esquerda = esquerda;
    }

    /**
     * Inicializa a Ã¡rvore direita.
     * @param direita A Ã¡rvore direita.
     */
    protected void setDireita(ArvoreBinariaPesquisa<E> direita) {
        this.direita = direita;
    }
     
    
    /**
     * Insere uma subÃ¡rvore Ã  esquerda deste nÃ³.
     * A subÃ¡rvore Ã  esquerda deste nÃ³ Ã© inserida na folha mais Ã  esquerda
     * da subÃ¡rvore inserida.
     * @param a A subÃ¡rvore a ser inserida.
     * @return A subÃ¡rvore inserida.
     */
    public ArvoreBinariaPesquisa<E> insereEsquerda(ArvoreBinariaPesquisa<E> a) {
        ArvoreBinariaPesquisa<E> e = esquerda;
        ArvoreBinariaPesquisa<E> x = a;
        esquerda = a;
        while (x.esquerda != null)
            x = x.esquerda;
        x.esquerda = e;
        return a;
    }
    
    /**
     * Insere uma subÃ¡rvore Ã  direita deste nÃ³.
     * A subÃ¡rvore Ã  direita deste nÃ³ Ã© inserida na folha mais Ã  direita
     * da subÃ¡rvore inserida.
     * @param a A subÃ¡rvore a ser inserida.
     * @return A subÃ¡rvore inserida.
     */
    public ArvoreBinariaPesquisa<E> insereDireita(ArvoreBinariaPesquisa<E> a) {
        ArvoreBinariaPesquisa<E> d = direita;
        ArvoreBinariaPesquisa<E> x = a;
        direita = a;
        while (x.direita != null)
            x = x.direita;
        x.direita = d;
        return a;
    }
    
    
    /**
     * Visita os nÃ³s da subÃ¡rvore em-ordem.
     * @param raiz A raiz da subÃ¡rvore
     */
    public void visitaEmOrdem(ArvoreBinariaPesquisa<E> raiz) {
        if (raiz != null) {
            visitaEmOrdem(raiz.esquerda);
            visita(raiz);
            visitaEmOrdem(raiz.direita);
        }
    }
    
    /**
     * Retorna o valor do prÃ³ximo nÃ³ em-ordem.
     * @return O valor do prÃ³ximo nÃ³ em-ordem.
     */
    public ArvoreBinariaPesquisa<E> proximoEmOrdem() {
        ArvoreBinariaPesquisa<E> resultado = null;
        if (inicio) {
            reinicia();
            inicio = false;
        }
        if (!pilha.isEmpty() || ultimoVisitado != null) {
            while (ultimoVisitado != null) {
                pilha.push(ultimoVisitado);
                ultimoVisitado = ultimoVisitado.esquerda;
            }
            ultimoVisitado = pilha.pop();
            resultado = ultimoVisitado;
            ultimoVisitado = ultimoVisitado.direita;
        }
        return resultado;
    }
    
    /**
     * Visita os nÃ³s da Ã¡rvore em-ordem a partir da raiz.
     */
    public void visitaEmOrdem() {
        visitaEmOrdem(this);
    }
    
    /**
     * Cria uma Ã¡rvore com valor nulo na raiz.
     */
    public ArvoreBinariaPesquisa() {
    }

    /**
     * Cria uma Ã¡rvore com o valor especificado na raiz.
     * @param valor O valor armazenado na raiz.
     */
    public ArvoreBinariaPesquisa(E valor) {
        super(valor);
    }

    /**
     * Inicializa o nÃ³ pai deste nÃ³.
     * @param pai O nÃ³ pai.
     */
    protected void setPai(ArvoreBinariaPesquisa<E> pai) {
        this.pai = pai;
    }

    /**
     * Retorna o nÃ³ pai deste nÃ³.
     * @return O nÃ³ pai.
     */
    protected ArvoreBinariaPesquisa<E> getPai() {
        return pai;
    }

    /**
     * OK
     * Retorna o nÃ³ da Ã¡rvore cujo valor corresponde ao especificado.
     * @param valor O valor a ser localizado.
     * @return A raiz da subÃ¡rvore contendo o valor ou {@code null}.
     */
    public ArvoreBinariaPesquisa<E> pesquisa(E valor)
    {
        ArvoreBinariaPesquisa<E> resultado = this; // o resultado estÃ¡ recebendo a raiz
        while( resultado != null && valor.compareTo(resultado.valor) != 0  ) // se for nulo ou a raiz
        {
            if( valor.compareTo(resultado.valor) < 0 && resultado.esquerda != null  )
                resultado = (ArvoreBinariaPesquisa)resultado.esquerda;
            else if(  valor.compareTo(resultado.valor) > 0 && resultado.direita != null )
                resultado = (ArvoreBinariaPesquisa)resultado.direita;
            else
                resultado = null;
        }
        return resultado;    
    }
    
    
    /**
     * OK
     * Retorna o nÃ³ da Ã¡rvore com o menor valor.
     * @return A raiz da subÃ¡rvore contendo o valor mÃ­nimo
     */
    public ArvoreBinariaPesquisa<E> getMinimo() // retorna um nÃ³
    {
        ArvoreBinariaPesquisa<E> resultado = this;
        while( resultado.esquerda != null ) { // o menor nÃ³ serÃ¡ o nÃ³ mais a esquerda, nÃ£o precisa ser a folha!
            resultado = (ArvoreBinariaPesquisa)resultado.esquerda; }
        return resultado;
    }

    /**
     * OK
     * Retorna o nÃ³ da Ã¡rvore com o maior valor.
     * @return A raiz da subÃ¡rvore contendo o valor mÃ¡ximo
     */
    public ArvoreBinariaPesquisa<E> getMaximo()
    {
        ArvoreBinariaPesquisa<E> resultado = this;
        while( resultado.direita != null ) { // o maior nÃ³ serÃ¡ o nÃ³ mais a direita, nÃ£o precisa ser a folha
            resultado = (ArvoreBinariaPesquisa)resultado.direita; }
        return resultado;
    }

    /**
     * OK
     * Retorna o nÃ³ sucessor do nÃ³ especificado.
     * @param no O nÃ³ cujo sucessor desejamos localizar
     * @return O sucessor do no ou {@null}.
     */
    public ArvoreBinariaPesquisa<E> sucessor(ArvoreBinariaPesquisa<E> no)
    {
        ArvoreBinariaPesquisa<E> resultado = no; // jÃ¡ estamos no nÃ³ que queremos pesquisar
        if( resultado != null && resultado.direita != null ) // OK sabemos que o nÃ³ estarÃ¡ na subÃ¡rvore
        {
            resultado = (ArvoreBinariaPesquisa)resultado.direita;
            if( resultado.esquerda != null )
            {
                while( resultado.esquerda != null ) {
                    resultado = (ArvoreBinariaPesquisa)resultado.esquerda; }
            }
        }
        else if( resultado != null && resultado.direita == null ) // o sucessor serÃ¡ um dos pais ou nÃ£o existe, verificando se nÃ£o Ã© apenas a raiz
        {
            while( resultado.pai != null )
            {
                resultado = resultado.pai; // jÃ¡ estamos no pai!
                if( resultado.esquerda != null && (resultado.esquerda.valor).compareTo(no.valor) == 0 ) { // filho pela esquerda -> achou!
                    break; } // para o programa
                else if( (resultado.valor).compareTo(this.valor) == 0 && (no.valor).compareTo(this.valor) > 0 ) // chegou na raiz e ela nÃ£o Ã© a resposta
                {
                    resultado = null;
                    break;
                }
            }
        }
        return resultado;
    }

    /**
     * OK
     * Retorna o nÃ³ predecessor do nÃ³ especificado.
     * @param no O nÃ³ cujo predecessor desejamos localizar
     * @return O predecessor do nÃ³ ou {@null}.
     */
    public ArvoreBinariaPesquisa<E> predecessor(ArvoreBinariaPesquisa<E> no)
    {
        ArvoreBinariaPesquisa<E> resultado = no; // jÃ¡ estamos no nÃ³ que queremos pesquisar
        if( resultado != null && resultado.esquerda != null ) 
        {
            resultado = (ArvoreBinariaPesquisa)resultado.esquerda;
            if( resultado.direita != null )
            {
                while( resultado.direita != null ) {
                    resultado = (ArvoreBinariaPesquisa)resultado.direita; }
            }
        }
        else if( resultado != null && resultado.esquerda == null ) // o sucessor serÃ¡ um dos pais ou nÃ£o existe, verificando se nÃ£o Ã© apenas a raiz
        {
            while( resultado.pai != null )
            {
                resultado = resultado.pai; // jÃ¡ estamos no pai!
                if( (resultado.valor).compareTo(no.valor) < 0 ) { // filho pela esquerda -> achou!
                    break; } // para o programa
                else if( (resultado.valor).compareTo(this.valor) == 0 && (no.valor).compareTo(this.valor) < 0 ) // chegou na raiz e ela nÃ£o Ã© a resposta
                {
                    resultado = null;
                    break;
                }
            }
        }
        return resultado;
    }

    /**
     * Insere um nÃ³ contendo o valor especificado na Ã¡rvore.
     * @param valor O valor armazenado no nÃ³.
     * @return O nÃ³ inserido
     */
    public ArvoreBinariaPesquisa<E> insere(E valor)
    {
        ArvoreBinariaPesquisa<E> resultado = this;
        ArvoreBinariaPesquisa<E> servo = null;
        
        while(valor.compareTo(resultado.valor) != 0) // para quando o resultado Ã© o resultado
        {
            if( valor.compareTo(resultado.valor) < 0 && resultado.esquerda != null  ) 
                resultado = (ArvoreBinariaPesquisa)resultado.esquerda;
            else if( valor.compareTo(resultado.valor) < 0 && resultado.esquerda == null ) // inserindo
            {
                servo = (ArvoreBinariaPesquisa)resultado; // salvando o pai
                resultado.insereEsquerda(new ArvoreBinariaPesquisa<>(valor));
                resultado = (ArvoreBinariaPesquisa)resultado.esquerda;
                resultado.pai = (ArvoreBinariaPesquisa)servo; // colocando o pai
            }
            else if( valor.compareTo(resultado.valor) > 0 && resultado.direita != null )
               resultado = (ArvoreBinariaPesquisa)resultado.direita;
            else if( valor.compareTo(resultado.valor) > 0 && resultado.direita == null ) // inserindo
            {
                servo = (ArvoreBinariaPesquisa)resultado; // salvando o pai
                resultado.insereDireita(new ArvoreBinariaPesquisa<>(valor));
                resultado = (ArvoreBinariaPesquisa)resultado.direita;
                resultado.pai = (ArvoreBinariaPesquisa)servo; // colocando o pai
            }
        }
        return resultado; // o programa para quando o resultado Ã© o resultado
    }

    /**
     * FAZENDOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
     * Exclui o nÃ³ especificado da Ã¡rvore.
     * Se a raiz for excluÃ­da, retorna a nova raiz.
     * @param no O nÃ³ a ser excluÃ­do.
     * @return A raiz da Ã¡rvore
     */
    public ArvoreBinariaPesquisa<E> exclui(ArvoreBinariaPesquisa<E> no)
    {
        // jÃ¡ recebemos o nÃ³
        no = pesquisa(no.valor);
        ArvoreBinariaPesquisa<E> resultado = no;
       
       // se o nÃ³ nÃ£o possui filhos
       if( resultado.direita == null && resultado.esquerda == null )
       {
           if( resultado.pai == null ) // Ã© a raiz alone
           {
               no.valor = null;
           }
           else
           {
               if( resultado == resultado.pai.direita )
                    resultado.pai.direita = null;
                else if( resultado == resultado.pai.esquerda )
                    resultado.pai.esquerda = null;
           }
       }
       
        // o nÃ³ possui apenas um filho
        else if( resultado.direita != null && resultado.esquerda == null ) // sabemos que nÃ£o serÃ¡ a raiz vazia
        {
            if( resultado.pai != null ) // sabemos que nÃ£o Ã© a raiz
            {
                resultado.direita.pai = resultado.pai; // arrumando o pai do filho
                if( resultado == resultado.pai.direita )
                    resultado.pai.direita = resultado.direita;
                else if( resultado == resultado.pai.esquerda )
                    resultado.pai.esquerda = resultado.direita;
            }
            else // se o nÃ³ nÃ£o tem pai estamos deletando a raiz
            {
                resultado.valor = resultado.direita.valor;
                resultado.direita = null;
            }
        }
       else if( resultado.direita == null && resultado.esquerda != null ) // sabemos que nÃ£o serÃ¡ a raiz vazia
        {
            if( resultado.pai != null )
            {
                resultado.esquerda.pai = resultado.pai; // arrumando o pai do filho
                if( resultado == resultado.pai.direita )
                    resultado.pai.direita = resultado.esquerda;
                else if( resultado == resultado.pai.esquerda )
                    resultado.pai.esquerda = resultado.esquerda;
            }
            else // se o nÃ³ nÃ£o tem pai estamos deletando a raiz
            {
                resultado.valor = resultado.esquerda.valor;
                resultado.esquerda = null;
            }
        }
       
       // o nÃ³ possui dois filhos
       else if( resultado.direita != null && resultado.esquerda != null )
       {
           while( resultado.direita != null && resultado.esquerda != null ) // tem no mÃ¡ximo um filho
           {
               resultado = sucessor(resultado); // pegando o sucessor do nÃ³
           }
           exclui(resultado);
           no.valor = resultado.valor;

       }
       return this; // retorna sempre a raiz da Ã¡rvore
    }
}
