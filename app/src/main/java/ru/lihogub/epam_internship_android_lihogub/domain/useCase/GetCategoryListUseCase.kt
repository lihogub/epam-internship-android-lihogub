package ru.lihogub.epam_internship_android_lihogub.domain.useCase

import io.reactivex.rxjava3.core.Single
import ru.lihogub.epam_internship_android_lihogub.domain.entity.CategoryEntity
import ru.lihogub.epam_internship_android_lihogub.domain.repository.CategoryRepository

class GetCategoryListUseCase(private val categoryRepository: CategoryRepository) {
    operator fun invoke(): Single<List<CategoryEntity>> = categoryRepository.getCategoryList()
}
