package name.kevinross.gradle.util

import org.gradle.api.Project
import org.gradle.plugins.signing.signatory.SignatorySupport
import org.gradle.plugins.signing.signatory.pgp.PgpKeyId
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Signatory using keybase command-line tool.
 */
class KeybaseSignatory extends SignatorySupport {
    private Logger logger = LoggerFactory.getLogger(KeybaseSignatory)
    private Project project
    private PgpKeyId keyId

    public KeybaseSignatory(Project prj, String key) {
        project = prj
        keyId = new PgpKeyId(key)
    }

    @Override
    String getName() {
        return "Keybase"
    }

    @Override
    void sign(InputStream inputStream, OutputStream outputStream) {
        logger.info("signing with key {}", keyId.asHex)
        project.exec {
            executable 'keybase'
            args 'pgp'
            args 'sign'
            args '--detached'
            args '--binary'
            if (keyId != null) {
                args '--key', keyId.asHex
            }
            standardInput = inputStream
            standardOutput = outputStream
        }
    }

    PgpKeyId getKeyId() {
        return keyId;
    }
}
