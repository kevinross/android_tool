package name.kevinross.gradle.util

/*
original: https://github.com/mdekstrand/gradle-plugins/blob/master/gradle-util/src/main/groovy/net/elehack/gradle/util/GnuPGSignatory.groovy
 */
import org.gradle.api.Project
import org.gradle.plugins.signing.SigningExtension
import org.gradle.plugins.signing.signatory.Signatory
import org.gradle.plugins.signing.signatory.SignatoryProvider

/**
 * Signatory provider to use the keybase command-line tool.  Assign an instance of this to the
 * {@code signing.signatories} property.
 */
class KeybaseSignatoryProvider implements SignatoryProvider {
    private Map<String,KeybaseSignatory> signatories = new HashMap()
    @Override
    void configure(SigningExtension signingExtension, Closure closure) {
        /* no-op - we do not support configuration. */
    }

    @Override
    Signatory getDefaultSignatory(Project project) {
        if (project.hasProperty('signing.keyId')) {
            return new KeybaseSignatory(project, project.getProperty('signing.keyId'))
        } else {
            return null
        }
    }

    @Override
    Signatory getSignatory(String s) {
        return signatories[s]
    }
}
