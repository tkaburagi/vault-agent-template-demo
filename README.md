# Setup

* Modifying path 
	* `local-vault-agent-approle-auth.hcl`
	* `applications.properties`

* Vault policy

```hcl
vault policy write read-gcp -<<EOF
path "gcp/*" {
  capabilities = ["read"]
}
EOF
```
* AppRole
```
vault write auth/approle/role/read-gcp token_policies="read-gcp"
vault read -format=json auth/approle/role/read-gcp/role-id | jq -r '.data.role_id'> roleid
vault write -force -format=json auth/approle/role/read-gcp/secret-id | jq -r '.data.secret_id'> secretid
```

* Start Vault Agent
```
vault agent -config=/Users/kabu/hashicorp/vault/configs/local-vault-agent-approle-auth.hcl -log-level=debug
```

* Generate GCP Key
```
vault token lookup $(cat approleToken)
export VAULT_AGENT_ADDR="http://127.0.0.1:8007"
VAULT_TOKEN=$(cat approleToken) vault read -format=json gcp/key/sql-admin > vault-output.json && cat vault-output.json | jq -r '.data.private_key_data' | base64 -D > gcp.key
```

* Start app
```
./mvnw clean package -DskipTests
java -jar target/demo-0.0.1-SNAPSHOT.jar
```
```