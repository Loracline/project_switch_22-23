package org.switch2022.project.ddd.datamodel_jpa;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Entity
@Table(name = "ProductBacklogs")
public class ProductBacklogJpa {
    /**
     * Attributes
     */
    @Id
    private String productBacklogId;
    @ElementCollection
    @CollectionTable(name = "UserStoriesInProductBacklog", joinColumns = @JoinColumn(name = "ProductBacklogId"))
    @Column(name = "userStoryInProductBacklog")
    private List<String> userStories;

    /**
     * Constructs a new instance of the ProductBacklogJpa class with the provided parameters.
     *
     * @param productBacklogId The identifier of the product backlog.
     * @param userStories      The list of user stories associated with the product backlog.
     */

    public ProductBacklogJpa(String productBacklogId, List<String> userStories) {
        this.productBacklogId = productBacklogId;
        this.userStories = Collections.unmodifiableList (userStories);
    }

    public String getProductBacklogId() {
        return productBacklogId;
    }

    public List<String> getUserStories() {
        return userStories;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        ProductBacklogJpa that = (ProductBacklogJpa) o;
        return Objects.equals(productBacklogId, that.productBacklogId) && Objects.equals(userStories, that.userStories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productBacklogId, userStories);
    }
}
