package cavaleiros;

public class CavaleiroBronze {
	private String nome;
	private float cosmo;
	private int energia;
	private boolean vivo;
	
	public CavaleiroBronze(String nome, float cosmo, int energia, boolean vivo) {
		this.setNome(nome);
		this.setCosmo(cosmo);
		this.setEnergia(energia);
		this.setVivo(vivo);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getCosmo() {
		return cosmo;
	}

	public void setCosmo(float cosmo) {
		this.cosmo = cosmo;
	}

	public int getEnergia() {
		return energia;
	}

	public void setEnergia(int energia) {
		this.energia = energia;
	}

	public boolean isVivo() {
		return vivo;
	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}
	

}
