/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ElGamal;

import java.math.BigDecimal;
import java.util.Random;

/**
 *
 * @author Arief
 */
public class ElGamalGenerator {

    private int publicKeyY;
    private int publicKeyG;
    private int publicKeyP;
    private int privateKeyX;
    
    public boolean isPrima(int n){
        boolean found = true;
        for (int i = 2; (i <= n/2 && found==true); i++) {
            if(n%i==0)
                found=false;
        }
        return found;
    }
    
    public void tampilFaktor(int n){
        for (int i = 1; i <= n/2; i++) {
            if(n%i==0)
                System.out.println(i);
        }
    }
    
    public int generateRandom(){
        Random randomGenerator = new Random();
        int outp;
        outp = randomGenerator.nextInt(10001); //limit ditentukan sendiri, angka 10001 dipilih agar perhitungan tidak terlalu besar
        return outp;
    }
    
    
    public int generateRandom(int limitbawah, int limitatas){
        int outp=1;
        do{
            outp=generateRandom();
        }while(outp>limitatas || outp<limitbawah);
        return outp;
    }
    
    public int generateRandomPrima(){
        int outp=1;
        do{
            outp=generateRandom();
        }while(!isPrima(outp));
        return outp;
    }
    
    public int generateRandomPrima(int limitbawah, int limitatas){
        int outp=1;
        do{
            outp=generateRandom();
        }while(!isPrima(outp) || outp>limitatas || outp<limitbawah);
        return outp;
    }
    
    public void generateKey(){
        setPublicKeyP(generateRandomPrima(258,10000)); // Limit ditentukan sendiri, angka 258 dipilih agar tidak terlalu kecil (agar bisa mengenkripsi integer sampai nilai 256), dan angka 1000 dipilih agar tidak terlalu besar
        setPublicKeyG(generateRandomPrima(1, getPublicKeyP()));
        setPrivateKeyX(generateRandomPrima(1,getPublicKeyP()-2));
        BigDecimal bD = BigDecimal.valueOf(getPublicKeyG()).pow(getPrivateKeyX()).remainder(BigDecimal.valueOf(getPublicKeyP()));
        setPublicKeyY(bD.intValue());
    }
    
    public int encryptA(int randomK){
        BigDecimal bD;
        BigDecimal hasil;
        bD = BigDecimal.valueOf(getPublicKeyG()).pow(randomK);
        hasil = bD.remainder(BigDecimal.valueOf(getPublicKeyP()));
        return hasil.intValue();
    }
    
    public int encryptB(int randomK, int plainChar){
        if(plainChar<0)
            plainChar=plainChar+256;
        BigDecimal bD;
        BigDecimal hasil;
        bD = BigDecimal.valueOf(getPublicKeyY()).pow(randomK).multiply(BigDecimal.valueOf(plainChar));
        hasil = bD.remainder(BigDecimal.valueOf(getPublicKeyP()));
        return hasil.intValue();
    }
    
    public int decrypt(int cipA, int cipB){
        BigDecimal bD = ((BigDecimal.valueOf(cipA)).pow(getPublicKeyP()-1-getPrivateKeyX())).remainder(BigDecimal.valueOf(getPublicKeyP()));
        int ax_1 = bD.intValue();
        int m = (cipB*ax_1)%getPublicKeyP();
        return m;
    }
    
    public String encryptString(String plaintext){
        String ciphertext="";
        for (int i = 0; i < plaintext.length(); i++) {
            int randomK = generateRandom(10, publicKeyP-2);
            int resultA = encryptA(randomK);
            int resultB = encryptB(randomK, (int)plaintext.charAt(i));
            ciphertext=ciphertext+ ((char) resultA);
            ciphertext=ciphertext+ ((char) resultB);
            if(i%1000==0)
                System.out.println("Encrypting char number : "+i);
        }
        ciphertext = convertStringtoHexString(ciphertext);
        return ciphertext;
    }
    
    public String encryptByte(byte[] plaintext){
        String ciphertext="";
        for (int i = 0; i < plaintext.length; i++) {
            int randomK = generateRandom(10, publicKeyP-2);
            //int randomK = 20;
            int resultA = encryptA(randomK);
            int resultB = encryptB(randomK, (int)plaintext[i]);
            ciphertext=ciphertext + ((char) resultA);
            ciphertext=ciphertext + ((char) resultB);
            if(i%1000==0)
                System.out.println("Encrypting byte number : "+i);
        }
        ciphertext = convertStringtoHexString(ciphertext);
        return ciphertext;
    }
    
    public String convertStringtoHexString (String S){
        String outp="";
        for (int i = 0; i < S.length(); i++) {
            outp=outp+Integer.toHexString((int)S.charAt(i))+" ";
        }
        return outp;
    }
    
    public byte[] DecryptByte(String S){
        String[] S2 = S.split(" ");
        byte[] outp = new byte[S2.length/2];
        for (int i = 0; i < S2.length/2; i++) {
            int ciph1 = Integer.parseInt(S2[i*2],16);
            int ciph2 = Integer.parseInt(S2[i*2+1],16);
            outp[i]=(byte) decrypt(ciph1, ciph2);
        }
        return outp;
    }
    
    public String DecryptString(String S){
        String[] S2 = S.split(" ");
        String outp="";
        for (int i = 0; i < S2.length/2; i++) {
            int ciph1 = Integer.parseInt(S2[i*2],16);
            int ciph2 = Integer.parseInt(S2[i*2+1],16);
            outp=outp+ (char)decrypt(ciph1, ciph2);
        }
        return outp;
    }
    
    /**
     * @return the publicKeyY
     */
    public int getPublicKeyY() {
        return publicKeyY;
    }

    /**
     * @return the publicKeyG
     */
    public int getPublicKeyG() {
        return publicKeyG;
    }

    /**
     * @return the publicKeyP
     */
    public int getPublicKeyP() {
        return publicKeyP;
    }

    /**
     * @return the privateKeyX
     */
    public int getPrivateKeyX() {
        return privateKeyX;
    }

    /**
     * @param publicKeyY the publicKeyY to set
     */
    public void setPublicKeyY(int publicKeyY) {
        this.publicKeyY = publicKeyY;
    }

    /**
     * @param publicKeyG the publicKeyG to set
     */
    public void setPublicKeyG(int publicKeyG) {
        this.publicKeyG = publicKeyG;
    }

    /**
     * @param publicKeyP the publicKeyP to set
     */
    public void setPublicKeyP(int publicKeyP) {
        this.publicKeyP = publicKeyP;
    }

    /**
     * @param privateKeyX the privateKeyX to set
     */
    public void setPrivateKeyX(int privateKeyX) {
        this.privateKeyX = privateKeyX;
    }
}