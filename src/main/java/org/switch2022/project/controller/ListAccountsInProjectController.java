package org.switch2022.project.controller;

import org.switch2022.project.model.Account;
import org.switch2022.project.model.Company;
import org.switch2022.project.utils.dto.AccountDTO;
import org.switch2022.project.utils.mapper.AccountMapper;

import java.util.ArrayList;
import java.util.List;


/**
 * Class ListAccountsInProjectController acts as an intermediary between the
 * user interface (UI) and the business logic underlying the "US014 - As
 * Manager, I want to get a list of all human resources in a project."
 */
public class ListAccountsInProjectController {
    /**
     * Attributes
     */
    private final Company company;

    /**
     * Constructor
     */
    public ListAccountsInProjectController(Company company) {
        this.company = company;
    }

    /**
     * This method lists all accounts that are working in a certain project.
     *
     * @param emailManager of the actor performing the task.
     * @param projectCode  of the project one searches for.
     * @return a list of accountDTOs carrying data about users working in
     * certain project.
     */
    public List<AccountDTO> listAccountsByProject(String emailManager, String projectCode) {
        List<AccountDTO> accountsDTO = new ArrayList<>();
        if (this.company.validateManager(emailManager)) {
            List<Account> accounts = this.company.listAccountsByProject(projectCode);
            accountsDTO = AccountMapper.listAccountsToDTO(accounts);
            // A COMPANY EM VEZ DE DEVOLVER UMA LISTA DE ACCOUNTS E SER O CONTROLLER
            // A CRIAR O DTO, PODIA SER A COMPANY A TER TODAS ESSAS RESPONSABILIDADES ATÉ PORQUE O CONTROLLER SÓ DEVERIA FAZER FLUXO DE INFORMAÇAO ( RECEBER UM PEDIDO E ENVIAR ESSE PEDIDO)
            // CONTUDO, COM O COMPANY JÁ ESTÁ A TER MUITAS RESPONSABILIDADES DECIDIMOS EM GRUPO ATRIBUIR ESSA RESPONSABILIDADE AO CONTROLLER SABENDO QUE PODERIA NAO SER O MAIS CORRETO.
            // NO FUTURO SERIA CRIADO UMA NOVA CAMADA ( SERVICE ) QUE ASSUMIRIA ENTAO ESSA TAREFA REMOVENDO ESSA RESPONSABILIDADE DO CONTROLLER).
        }
        return accountsDTO;
    }
}
