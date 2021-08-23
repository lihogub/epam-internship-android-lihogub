package ru.lihogub.epam_internship_android_lihogub.domain.useCase

import ru.lihogub.epam_internship_android_lihogub.domain.repository.CategoryRepository
import javax.inject.Inject

class SaveLastCategoryUseCase @Inject constructor(var categoryRepository: CategoryRepository) {
    operator fun invoke(categoryName: String) = categoryRepository.setLastCategoryName(categoryName)
}