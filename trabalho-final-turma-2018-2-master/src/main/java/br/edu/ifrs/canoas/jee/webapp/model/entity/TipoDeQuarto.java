package br.edu.ifrs.canoas.jee.webapp.model.entity;

public enum TipoDeQuarto {
	STANDARD("Standard"), 
	DUPLO("Duplo"), 
	TRIPLO("Triplo"), 
	MASTER("Master"), 
	PRESIDENCIAL("Presidencial"), 
	MEGA_ULTRA("Mega Ultra");
	
	private String descricao;
	
    TipoDeQuarto(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
