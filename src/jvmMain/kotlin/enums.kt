package com.mlreef.rest.external_api.gitlab

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue


enum class GitlabAccessLevel(val accessCode: Int) {
    GUEST(10),
    REPORTER(20),
    DEVELOPER(30),
    MAINTAINER(40),
    OWNER(50);

    companion object {
        @JvmStatic
        @JsonCreator
        fun fromCode(code: Int?): GitlabAccessLevel? {
            return values().firstOrNull { it.accessCode == code }
        }

        fun isSufficientFor(instance: GitlabAccessLevel?, limit: GitlabAccessLevel?): Boolean {
            if (limit == null) return true
            if (instance == null) return false
            return instance.accessCode >= limit.accessCode
        }
    }

    fun satisfies(limit: GitlabAccessLevel?) = GitlabAccessLevel.isSufficientFor(this, limit)
}

enum class VariableType {
    ENV_VAR,
    FILE;

    @JsonValue
    open fun getValue(): String {
        return this.name.toLowerCase()
    }
}

enum class GitlabVisibility {
    PRIVATE,
    INTERNAL,
    PUBLIC;

    companion object {
        val default = PUBLIC
    }
}


enum class ProjectCreationLevel {
    NOONE,
    MAINTAINER,
    DEVELOPER
}

enum class SubgroupCreationLevel {
    OWNER,
    MAINTAINER
}

enum class GitlabActivityState {
    ACTIVE,
    INACTIVE
}

enum class NamespaceKind {
    USER,
    GROUP
}

enum class GitStrategy {
    FETCH,
    MERGE
}

enum class GitlabState {
    ENABLED,
    DISABLED
}

enum class MergeMethod {
    MERGE,
    REBASE_MERGE,
    FF
}

enum class DeployStrategy {
    CONTINUOUS,
    MANUAL,
    TIMED_INCREMENTAL
}

enum class RepositoryTreeType {
    TREE,
    BLOB,
}

