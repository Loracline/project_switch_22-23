package org.switch2022.project.ddd.datamodel_jpa;

import lombok.Data;
import lombok.Getter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "productBacklog")
public class ProductBacklogJpa {
    /**
     * Attributes
     */
    @Id
    @Getter
    private String productBacklogId;
    @ElementCollection
    private List<String> userStories;

    /**
     * Constructs a new instance of the ProductBacklogJpa class with the provided parameters.
     *
     * @param productBacklogId The identifier of the product backlog.
     * @param userStories      The list of user stories associated with the product backlog.
     */

    public ProductBacklogJpa(String productBacklogId, List<String> userStories) {
        this.productBacklogId = productBacklogId;
        this.userStories = userStories;
    }

    protected ProductBacklogJpa() {
    }
}
