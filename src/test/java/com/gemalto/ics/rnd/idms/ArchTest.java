package com.gemalto.ics.rnd.idms;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.gemalto.ics.rnd.idms");

        noClasses()
            .that()
            .resideInAnyPackage("com.gemalto.ics.rnd.idms.service..")
            .or()
            .resideInAnyPackage("com.gemalto.ics.rnd.idms.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.gemalto.ics.rnd.idms.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
