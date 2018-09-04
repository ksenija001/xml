package xml.projekat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xml.projekat.Model.CategoryType;

@Repository
public interface CategoryTypeRepository extends JpaRepository<CategoryType, Long> {

	public CategoryType findById(Long id);
}
