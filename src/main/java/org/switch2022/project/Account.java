package org.switch2022.project;

import java.awt.image.BufferedImage;

public class Account {
        private String name;
        private String email;
        private String password;
        private boolean statusAccount;

        private String status="activate"; /*corrigir a forma como deve voltar o status com boolean*/
        private long phoneNumber; /*Optei pelo formato long porque, um número de telefone pode ter até 14 dígitos, se for internacional, e o int só vai até 10 dígitos*/
        private BufferedImage photo;/*variável utilizada para imagens em Java*/
        private Profile profile;

    public Account(String name, String email, String password, boolean statusAccount, long phoneNumber, BufferedImage photo,Profile profile){
        this.name =name;
        this.email=email;
        this.password=password;
        this.statusAccount= statusAccount;
        this.phoneNumber=phoneNumber;
        this.photo= photo;
        this.profile=profile;
    }
        public String getName() { /*get se usa para acessar um atributo privado*/

        return this.name;
    }
        public String getEmail(){
        return this.email;
    }
        public String getPassword(){
        return this.password;
    }
        public boolean isStatusAccount(){ /*corrigir a forma como deve voltar o status com boolean*/
        return statusAccount;
    }
        public long getPhoneNumber(){
        return this.phoneNumber;
    }
        public BufferedImage getPhoto(){
        return this.photo;
    }
        public Profile getProfile(){
        return this.profile;
    }
        public boolean statusAccount(String status){ /*corrigir a forma como deve voltar o status com boolean*/
        boolean condition= true;
        if (status=="inactivate"){
            condition= false;}
        return condition;
    }

        @Override /*garantia de que está sobrescrevendo um método e não criando um novo.*/
        public boolean equals (Object obj) {
        boolean condition = false;
        if (obj instanceof Account) {
            Account accountTest = (Account) obj;
            if (this.name == accountTest.email) {
                condition = true;
            }
        }return condition;
    }
}
