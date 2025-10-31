package br.edu.ufam.icomp.ru_digital.entities.consumivel;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.HashSet;
import java.util.Set;

/**
 * Descreve os atributos e métodos básicos de um consumível genérico. Foi usado o conceito
 * de <b>classe abstrata</b> para prevenir a instanciação de consumíveis genéricos.
 * <p>
 * Qualquer consumível deve ter:
 * <ul>
 *  <li>Um identificador único imutável, gerado automaticamente;</li>
 *  <li>Um nome humanamente legível;</li>
 *  <li>Uma descrição curta do alimento;</li>
 *  <li>Um conjunto de possíveis alérgenos presentes no consumível.</li>
 * </ul>
 */
@Entity
@Table(name = "consumivel")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_consumivel", discriminatorType = DiscriminatorType.STRING)
public abstract class Consumivel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(length = 500)
    private String descricao;

    @ElementCollection
    @CollectionTable(name = "consumivel_alergenos", joinColumns = @JoinColumn(name = "consumivel_id"))
    @Column(name = "alergeno")
    private Set<String> alergenos;

    /**
     * Construtor padrão.
     */
    public Consumivel() {
        this.alergenos = new HashSet<>();
    }

    /**
     * Cria um consumível dado seu nome.
     * @param nome O nome do consumível.
     */
    public Consumivel(@NonNull String nome) {
        this.nome = nome;
        this.alergenos = new HashSet<>();
    }

    /**
     * Cria um consumível dado seu nome e descrição.
     * @param nome O nome do consumível.
     * @param descricao Uma descrição curta do consumível.
     */
    public Consumivel(@NonNull String nome, @NonNull String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        this.alergenos = new HashSet<>();
    }

    /**
     * @return Um ID único do consumível.
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return O nome do consumível, da forma que deve ser mostrado em uma interface.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do consumível, da forma que deve ser mostrado em uma interface.
     * @param nome O novo nome do consumível.
     */
    public void setNome(@NonNull String nome) {
        this.nome = nome;
    }

    /**
     * @return Uma descrição curta do consumível, da forma que deve ser mostrada em uma interface.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição do consumível, da forma que deve ser mostrada em uma interface.
     * @param descricao Uma nova descrição curta do consumível.
     */
    public void setDescricao(@NonNull String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return Um conjunto de possíveis alérgenos presentes no consumível.
     */
    public Set<String> getAlergenos() {
        return alergenos;
    }

    public void setAlergenos(Set<String> alergenos) {
        this.alergenos = alergenos;
    }
}
