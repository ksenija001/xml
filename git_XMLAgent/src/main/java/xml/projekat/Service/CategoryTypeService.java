package xml.projekat.Service;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml.projekat.Model.Accommodation;
import xml.projekat.Model.CategoryType;
import xml.projekat.Repository.AccommodationRepository;
import xml.projekat.Repository.CategoryTypeRepository;

@Service
public class CategoryTypeService {

	@Autowired
	private CategoryTypeRepository categoryTypeRepository;

	@Autowired
	private AccommodationRepository accommodationRepository;
	
	public List<CategoryType> findAll() {
		return categoryTypeRepository.findAll();
	}

	public CategoryType findById(Long id) {
		return categoryTypeRepository.findById(id);
	}
	
	public List<CategoryType> createCategoryType(String type) {
		CategoryType createCategoryType = new CategoryType();
		createCategoryType.setType(type);
		categoryTypeRepository.save(createCategoryType);
		return findAll();
	}
	
	public List<CategoryType> deleteCategoryType(Long id) {
		
		for(Accommodation a : accommodationRepository.findAll()) {
			if(a.getCategory().getId().equals(id)) {
				a.setCategory(null);
				accommodationRepository.save(a);
			}
		}
		CategoryType deletedCategoryType = categoryTypeRepository.findById(id);
		categoryTypeRepository.delete(deletedCategoryType);
		return findAll();
	}

	
	public List<CategoryType> editCategoryType(Long id, String category) {
		CategoryType editedCategoryType = categoryTypeRepository.findById(id);
		editedCategoryType.setType(category);
		categoryTypeRepository.save(editedCategoryType);
		return findAll();
	}
}
