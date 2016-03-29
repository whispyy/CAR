# CAR - TD6
*01/03/2016*
---

1.

*-/fr/unv-lille1/fil*

On remonte de la racine en direction des feuilles. Par exemple http://fil.unv-lille1.fr va partir de la racine vers fr puis univ-lille1 puis fil. 

Le role de l'annuaire est de faire le lien entre l'adresse physique et logique.

2.

```
ProcessusClient
  modifier
  retirer
  chercher
SocketTCP
  string 'ajouter';
  string adresseLogique;
  string adressePhysique.adresse;
  int adressePhysique.port;
```

3.

4.

``` java
public class Client{
  public void main(Strings[] args){
    Annuaire annaurai = new AnnuaireStub();
    annuaire.ajouter("car", new Adresse("rac.lifl.fr",5896));
    annuaire.ajouter("m1",new Adresse("master.lifl.fr",698));
    annuaire.lister();
  }
}
public class AnnuaireStub implements Annuaire{
  private Socket s;
  private ObjectInputSteam is;
  private ObjectOutputSteam os,

  public AnnuaireStub(){
    this.s = new Socket("annuaire.lifl.fr",89);
    this.is = s.getInputStream();
    this.os = s.getOutputStream();
  }
  public boolean ajouter(String adrP, String adr){
    os.writeUTF("ajouter");
    os.writeUTF("adr");
    os.writeUTF(adrP.adresse);
    os.writeINT(adrP.port);
    return isReadBoolean();
  }
  public String[] lister(){
    String[] list;
    os.writeUTF("lister");
    int size = isreadInt();
    list = new String[size];
    for (int i = 0; i < size; i++)
      list[i] = isreadUTF();
    return list;
  }
}
```

5.

6.
```java
public Class AnnuaireSkel{
  private SocketServer server;
  private Annuaire annuaire;
  public AnnuaireSkel{
    server = new SocketServer(89);
    annuaire = new Annuaire(..);
    process();
  }
  private void process(){
    while (true){
      Socket c = server.accept();
      InputStream is = c.getInputStream();
      OutputStream os = c.getOutputStream();
      switch(is:readUTF()){
        case "ajouter"
          String logique = is.readUTF();
          String physique = is.readUTF();
          int p = is.readINT();
          os.writeBoolean(annuaire,ajouter(logique,new Adresse(physique, p)));
        break;
        case "lister"
          String list[] = annuaire.lister();
          os.writeInt(list.size());
          for (int i=0 ; i < list.size();i++){
            os.writeUTF(lsit[i]);
          break;
        }
      }
    }
  }
}
public class AnnuaireImpl implements Annuaire{

}
```